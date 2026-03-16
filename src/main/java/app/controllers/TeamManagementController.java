package app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import java.util.*;

import app.models.Pokemon;

public class TeamManagementController {

    @FXML private ComboBox<String> cb1, cb2, cb3;
    @FXML private CheckBox lock1, lock2, lock3; 
    @FXML private StackPane displayArea;
    @FXML private Button btnValidate;

    private final Map<String, Pokemon> pokedex = new HashMap<>();
    // Guard flag: blocks onAction events fired by setItems()/setValue()
    private boolean isUpdating = false;

    private final Map<String, String> typeColors = Map.ofEntries(
            Map.entry("Insecte",    "#9EE84F"),
            Map.entry("Tenebres",   "#2D2E28"),
            Map.entry("Dragon",     "#7946B0"),
            Map.entry("Electrique", "#F2BA1F"),
            Map.entry("Fee",        "#FA9DCF"),
            Map.entry("Combat",     "#4D240B"),
            Map.entry("Feu",        "#910000"),
            Map.entry("Vol",        "#6298C4"),
            Map.entry("Spectre",    "#393166"),
            Map.entry("Plante",     "#0AA119"),
            Map.entry("Sol",        "#C79442"),
            Map.entry("Glace",      "#92C8D1"),
            Map.entry("Normal",     "#A6A6A6"),
            Map.entry("Poison",     "#7804CC"),
            Map.entry("Acier",      "#A6988A"),
            Map.entry("Eau",        "#1656DB"),
            Map.entry("Psy",        "#FF6B9D")
    );

    // -------------------------------------------------------------------------
    // Initialization
    // -------------------------------------------------------------------------

    @FXML
    public void initialize() {
        fillPokedex();
        setupRow(cb1, lock1);
        setupRow(cb2, lock2);
        setupRow(cb3, lock3);
        refreshLists();
        showWelcomeScreen();
    }

    private void setupRow(ComboBox<String> cb, CheckBox lock) {
        cb.setOnAction(e -> onComboChanged(cb, lock));
        lock.setOnAction(e -> onLockToggled(cb, lock));
    }

    private void onComboChanged(ComboBox<String> cb, CheckBox lock) {
        if (isUpdating) return;
        if (cb.getValue() != null) {
            updateDisplay(cb.getValue());
            lock.setDisable(false);
            refreshLists();
        }
        checkValidation();
    }

    private void onLockToggled(ComboBox<String> cb, CheckBox lock) {
        cb.setDisable(lock.isSelected());
        refreshLists();
        checkValidation();
    }

    // -------------------------------------------------------------------------
    // List management (no duplicates)
    // -------------------------------------------------------------------------

    private void refreshLists() {
        Set<String> selected = getSelectedNames();
        isUpdating = true;
        for (ComboBox<String> b : getComboBoxes()) {
            updateComboItems(b, selected);
        }
        isUpdating = false;
    }

    private Set<String> getSelectedNames() {
        Set<String> selected = new HashSet<>();
        for (ComboBox<String> b : getComboBoxes()) {
            if (b.getValue() != null) selected.add(b.getValue());
        }
        return selected;
    }

    private List<ComboBox<String>> getComboBoxes() {
        return Arrays.asList(cb1, cb2, cb3);
    }

    private void updateComboItems(
            ComboBox<String> b,
            Set<String> selected) {
        String current = b.getValue();
        List<String> available = new ArrayList<>(pokedex.keySet());
        available.removeIf(
                n -> selected.contains(n) && !n.equals(current)
        );
        Collections.sort(available);
        b.setItems(FXCollections.observableArrayList(available));
        b.setValue(current);
    }

    // -------------------------------------------------------------------------
    // Validation
    // -------------------------------------------------------------------------

    private void checkValidation() {
        boolean allSelected =
                cb1.getValue() != null
                        && cb2.getValue() != null
                        && cb3.getValue() != null;
        boolean allDifferent =
                allSelected
                        && !cb1.getValue().equals(cb2.getValue())
                        && !cb1.getValue().equals(cb3.getValue())
                        && !cb2.getValue().equals(cb3.getValue());
        boolean allLocked =
                lock1.isSelected()
                        && lock2.isSelected()
                        && lock3.isSelected();
        btnValidate.setDisable(!(allDifferent && allLocked));
    }

    @FXML
    private void handleValidate() {
        btnValidate.setText("MISSION VALIDÉE ✓");
    }

    // -------------------------------------------------------------------------
    // Welcome screen
    // -------------------------------------------------------------------------

    private void showWelcomeScreen() {
        VBox welcome = new VBox(35);
        welcome.setAlignment(Pos.CENTER);
        welcome.setStyle("-fx-background-color: #1a1e24;");
        welcome.getChildren().addAll(
                buildWelcomeHeader(),
                buildWelcomeSeparator(),
                buildRosterTitle(),
                buildRosterGrid(),
                buildWelcomeHint()
        );
        displayArea.getChildren().setAll(welcome);
    }

    private VBox buildWelcomeHeader() {
        Label title = new Label("Javamon Lab");
        title.setStyle(
                "-fx-font-size: 52;"
                        + " -fx-font-weight: 900;"
                        + " -fx-text-fill: #e2e8f0;"
        );
        Label subtitle = new Label(
                "Sélectionne trois Javamon pour constituer ton équipe"
        );
        subtitle.setStyle("-fx-font-size: 13; -fx-text-fill: #64748b;");
        VBox header = new VBox(8, title, subtitle);
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private javafx.scene.shape.Rectangle buildWelcomeSeparator() {
        javafx.scene.shape.Rectangle sep =
                new javafx.scene.shape.Rectangle(60, 2);
        sep.setFill(Color.web("#334155"));
        return sep;
    }

    private Label buildRosterTitle() {
        Label lbl = new Label("ROSTER");
        lbl.setStyle(
                "-fx-font-size: 11;"
                        + " -fx-text-fill: #475569;"
                        + " -fx-font-weight: bold;"
        );
        return lbl;
    }

    private javafx.scene.layout.FlowPane buildRosterGrid() {
        javafx.scene.layout.FlowPane roster =
                new javafx.scene.layout.FlowPane(8, 8);
        roster.setAlignment(Pos.CENTER);
        roster.setMaxWidth(680);
        List<String> names = new ArrayList<>(pokedex.keySet());
        Collections.sort(names);
        for (String name : names) {
            roster.getChildren().add(buildRosterCard(name));
        }
        return roster;
    }

    private VBox buildRosterCard(String name) {
        Pokemon p = pokedex.get(name);
        String[] types = Arrays.stream(p.getTypes()).map(Type::getName).toArray(String[]::new); 
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setStyle(buildCardStyle("#252b35"));
        card.getChildren().addAll(
                buildCardName(name),
                buildTypeBadges(types)
        );
        card.setOnMouseClicked(e -> updateDisplay(name));
        card.setOnMouseEntered(e -> card.setStyle(buildCardStyle("#2e3744")));
        card.setOnMouseExited(e -> card.setStyle(buildCardStyle("#252b35")));
        return card;
    }

    private String buildCardStyle(String bgColor) {
        return "-fx-background-color: " + bgColor + ";"
                + " -fx-background-radius: 8;"
                + " -fx-padding: 10 18;"
                + " -fx-cursor: hand;";
    }

    private Label buildCardName(String name) {
        Label lbl = new Label(name);
        lbl.setStyle(
                "-fx-font-size: 13;"
                        + " -fx-font-weight: bold;"
                        + " -fx-text-fill: #cbd5e1;"
        );
        return lbl;
    }

    private HBox buildTypeBadges(String[] types) {
        HBox box = new HBox(5);
        box.setAlignment(Pos.CENTER);
        for (String t : types) {
            box.getChildren().add(buildTypeBadge(t));
        }
        return box;
    }

    private Label buildTypeBadge(String type) {
        String color = typeColors.getOrDefault(type, "#444");
        Label badge = new Label(type);
        badge.setStyle(
                "-fx-background-color: " + color + "99;"
                        + " -fx-text-fill: white;"
                        + " -fx-font-size: 9;"
                        + " -fx-font-weight: bold;"
                        + " -fx-padding: 2 7;"
                        + " -fx-background-radius: 6;"
        );
        return badge;
    }

    private Label buildWelcomeHint() {
        Label hint = new Label(
                "Clique sur une carte pour prévisualiser un Javamon"
        );
        hint.setStyle(
                "-fx-font-size: 11;"
                        + " -fx-text-fill: #334155;"
                        + " -fx-font-style: italic;"
        );
        return hint;
    }

    // -------------------------------------------------------------------------
    // Pokemon display
    // -------------------------------------------------------------------------

    private void updateDisplay(String name) {
        Pokemon p = pokedex.get(name);
        String[] types = Arrays.stream(p.getTypes()).map(Type::getName).toArray(String[]::new); 
        String c1 = typeColors.getOrDefault(types[0], "#3b82f6");
        String c2 = (types.length > 1)
                ? typeColors.getOrDefault(types[1], c1)
                : c1;
        VBox container = buildDisplayContainer(c1, c2);
        container.getChildren().addAll(
                buildHeader(j, types),
                buildMidSection(j, c1),
                buildFooter(j)
        );
        displayArea.getChildren().setAll(container);
    }

    private VBox buildDisplayContainer(String c1, String c2) {
        VBox container = new VBox(25);
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(30));
        container.setStyle(
                "-fx-background-color: linear-gradient("
                        + "to bottom right, "
                        + darken(c1, 0.8) + ", "
                        + darken(c2, 0.6) + ");"
        );
        return container;
    }

    private HBox buildHeader(Javamon j, String[] types) {
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        Label lblName = new Label(j.getName().toUpperCase());
        lblName.setStyle(
                "-fx-font-size: 50;"
                        + " -fx-font-weight: 900;"
                        + " -fx-text-fill: white;"
        );
        header.getChildren().add(lblName);
        for (String t : types) {
            header.getChildren().add(buildHeaderBadge(t));
        }
        return header;
    }

    private Label buildHeaderBadge(String type) {
        String color = typeColors.getOrDefault(type, "#444");
        Label badge = new Label(type.toUpperCase());
        badge.getStyleClass().add("type-badge");
        badge.setStyle("-fx-background-color: " + color + ";");
        return badge;
    }

    private HBox buildMidSection(Javamon j, String c1) {
        HBox mid = new HBox(50);
        mid.setAlignment(Pos.CENTER);
        mid.getChildren().addAll(
                buildPokemonImage(j),
                buildRadarBox(j, c1)
        );
        return mid;
    }

    private ImageView buildPokemonImage(Javamon j) {
        ImageView iv = new ImageView();
        var stream = getClass()
                .getResourceAsStream("Images/" + j.getName()+".png");
        if (stream != null) iv.setImage(new Image(stream));
        iv.setFitWidth(350);
        iv.setPreserveRatio(true);
        return iv;
    }

    private VBox buildRadarBox(Javamon j, String c1) {
        Label radarTitle = new Label("Statistiques");
        radarTitle.setStyle(
                "-fx-font-weight: bold;"
                        + " -fx-font-size: 15;"
                        + " -fx-text-fill: #1e293b;"
        );
        VBox box = new VBox(10, radarTitle, drawRadar(j, c1));
        box.getStyleClass().add("radar-pane");
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private HBox buildFooter(Javamon j) {
        HBox footer = new HBox(30);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().addAll(
                createCard(
                        "Description", j.getDescription(),
                        "card-white", "#1e293b"
                ),
                createCard(
                        "Lore", j.getLore(),
                        "card-dark", "#f8fafc"
                )
        );
        return footer;
    }

    private VBox createCard(
            String title, String text,
            String styleClass, String textColor) {
        Label t = new Label(title);
        t.setStyle(
                "-fx-font-weight: bold;"
                        + " -fx-font-size: 14;"
                        + " -fx-text-fill: " + textColor + ";"
        );
        Label c = new Label(text);
        c.setStyle("-fx-text-fill: " + textColor + ";");
        c.setWrapText(true);
        c.setPrefWidth(380);
        VBox v = new VBox(10, t, c);
        v.getStyleClass().add(styleClass);
        return v;
    }

    // -------------------------------------------------------------------------
    // Radar chart
    // -------------------------------------------------------------------------

    private Pane drawRadar(Pokemon p, String typeColor) {
        Pane p = new Pane();
        p.setMinSize(260, 260);
        double center = 130, r = 90;
        int[] stats = {
                j.getHp(), j.getAtk(), j.getDef(),
                j.getSpAtk(), j.getSpDef(), j.getSpeed()
        };
        p.getChildren().add(buildHexOutline(center, r));
        addAxesAndLabels(p, center, r, stats);
        p.getChildren().add(buildStatPolygon(center, r, stats, typeColor));
        return p;
    }

    private Polygon buildHexOutline(double center, double r) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(i * 60 - 90);
            hex.getPoints().addAll(
                    center + r * Math.cos(angle),
                    center + r * Math.sin(angle)
            );
        }
        hex.setFill(Color.TRANSPARENT);
        hex.setStroke(Color.BLACK);
        hex.setStrokeWidth(1.5);
        hex.getStrokeDashArray().addAll(5.0, 3.0);
        return hex;
    }

    private void addAxesAndLabels(
            Pane p, double center,
            double r, int[] stats) {
        String[] labels = {"PV", "ATK", "DEF", "A.SP", "D.SP", "VIT"};
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(i * 60 - 90);
            p.getChildren().add(buildAxis(center, r, angle));
            p.getChildren().add(
                    buildAxisLabel(center, r, angle, labels[i], stats[i])
            );
        }
    }

    private Line buildAxis(double center, double r, double angle) {
        Line axis = new Line(
                center, center,
                center + r * Math.cos(angle),
                center + r * Math.sin(angle)
        );
        axis.setStroke(Color.BLACK);
        axis.setStrokeWidth(1.5);
        return axis;
    }

    private Label buildAxisLabel(
            double center, double r, double angle,
            String name, int value) {
        Label l = new Label(name + "\n" + value);
        l.getStyleClass().add("radar-label");
        l.setLayoutX(center + (r + 22) * Math.cos(angle) - 18);
        l.setLayoutY(center + (r + 22) * Math.sin(angle) - 15);
        return l;
    }

    private Polygon buildStatPolygon(
            double center, double r,
            int[] stats, String typeColor) {
        Polygon poly = new Polygon();
        for (int i = 0; i < 6; i++) {
            double val = (Math.min(stats[i], 255) / 255.0) * r;
            double angle = Math.toRadians(i * 60 - 90);
            poly.getPoints().addAll(
                    center + val * Math.cos(angle),
                    center + val * Math.sin(angle)
            );
        }
        applyPolygonColor(poly, typeColor);
        return poly;
    }

    private void applyPolygonColor(Polygon poly, String typeColor) {
        try {
            Color c = Color.web(typeColor);
            poly.setFill(
                    new Color(c.getRed(), c.getGreen(), c.getBlue(), 0.45)
            );
            poly.setStroke(c);
            poly.setStrokeWidth(2);
        } catch (Exception e) {
            poly.getStyleClass().add("radar-poly-fill");
        }
    }

    // -------------------------------------------------------------------------
    // Utilities
    // -------------------------------------------------------------------------

    private String darken(String hex, double factor) {
        try {
            Color c = Color.web(hex);
            int r = (int) (c.getRed()   * 255 * factor);
            int g = (int) (c.getGreen() * 255 * factor);
            int b = (int) (c.getBlue()  * 255 * factor);
            return String.format("#%02x%02x%02x",
                    Math.max(0, Math.min(255, r)),
                    Math.max(0, Math.min(255, g)),
                    Math.max(0, Math.min(255, b))
            );
        } catch (Exception e) {
            return hex;
        }
    }

    // -------------------------------------------------------------------------
    // Pokedex data
    // -------------------------------------------------------------------------

    private void fillPokedex() {
        addJavamon("Crystalion", "Dragon/Glace", 60, 55, 65, 115, 70, 110,
                "Ce petit dragon vit dans les grottes de cristal les plus"
                        + " profondes. Son corps est composé de gemmes semi-précieuses"
                        + " qui emmagasinent la lumière pour la rejeter sous forme"
                        + " de rayons laser dévastateurs.",
                "On dit que si l'un de ses éclats tombe, il repousse en une"
                        + " semaine. Les mineurs le suivent souvent, car sa présence"
                        + " indique la proximité de gisements rares.",
                "Crystalion.png", "#7946B0");
        addJavamon("Roncigami", "Plante/Normal", 75, 90, 130, 50, 95, 40,
                "Un Pokémon composé de feuilles rigides pliées comme du papier."
                        + " Ses bords sont aussi tranchants que des lames de rasoir,"
                        + " et il se déplace avec une rigidité mécanique.",
                "Il est né d'une légende racontant qu'un maître en origami"
                        + " aurait insufflé la vie à ses créations pour protéger son"
                        + " jardin. Il ne flétrit jamais, même en plein hiver.",
                "Roncigami.png", "#0AA119");
        addJavamon("Towltem", "Sol", 100, 60, 85, 105, 140, 10,
                "Un automate massif dont le buste contient un sablier magique."
                        + " Il ne bouge presque jamais, mais ses yeux brillent"
                        + " intensément quand le temps s'écoule.",
                "On raconte que Towltem peut ralentir les battements de cœur"
                        + " de ses adversaires en inversant son sablier. Il est le"
                        + " gardien des ruines oubliées par l'histoire.",
                "Towltem.png", "#C79442");
        addJavamon("Rubrasier", "Feu/Spectre", 50, 120, 45, 100, 45, 145,
                "Un serpent de pure énergie thermique qui ondule dans les airs"
                        + " comme un ruban de soie en feu. Il ne touche jamais le sol.",
                "Il est capable de traverser les murs en les faisant fondre"
                        + " instantanément. Apercevoir un Rubrasier dans le ciel"
                        + " nocturne est un signe de canicule imminente.",
                "Rubrasier.png", "#910000");
        addJavamon("Moustix", "Insecte/Poison", 70, 95, 80, 95, 80, 85,
                "Un insecte bourdonnant entouré d'un nuage de spores toxiques."
                        + " Ses yeux multiples lui permettent de détecter les"
                        + " faiblesses biologiques de ses proies.",
                "Ce Pokémon prospère dans les milieux pollués. Les orbes verts"
                        + " sur son dos contiennent un venin capable de paralyser un"
                        + " Rhino en quelques secondes.",
                "Moustix.png", "#9EE84F");
        addJavamon("Orbitacle", "Vol/Tenebres", 85, 40, 80, 125, 110, 75,
                "Une entité dont la tête ressemble à une planète entourée"
                        + " d'anneaux de débris spatiaux. Ses tentacules flottent"
                        + " comme s'ils étaient en apesanteur.",
                "On pense qu'Orbitacle est tombé des étoiles il y a des"
                        + " millénaires. Il communique par ondes radio que les humains"
                        + " captent parfois comme des sifflements.",
                "Orbitacle.png", "#6298C4");
        addJavamon("Marietta", "Fee", 65, 45, 70, 105, 125, 80,
                "Une marionnette rose aux yeux de bouton tenue par des fils"
                        + " invisibles reliés à une main fantomatique flottant"
                        + " au-dessus d'elle.",
                "Bien qu'elle ait l'air inoffensive, c'est elle qui contrôle"
                        + " la main spectrale. Elle adore égarer les voyageurs"
                        + " dans la brume.",
                "Marietta.png", "#FA9DCF");
        addJavamon("Frimanta", "Glace/Eau", 80, 70, 75, 110, 90, 105,
                "Une raie majestueuse faite de glace éternelle. Ses ailes"
                        + " cristallines scintillent et laissent une traînée de"
                        + " poudreuse givrée.",
                "Elle survole les océans arctiques en silence. Son chant peut"
                        + " geler une vague entière, créant des ponts de glace pour"
                        + " les Pokémon terrestres.",
                "Frimanta.png", "#92C8D1");
        addJavamon("Frapphino", "Combat", 115, 140, 110, 20, 65, 60,
                "Un rhinocéros bipède massif portant des gants de boxe naturels"
                        + " et une ceinture de champion. Son regard exprime une"
                        + " détermination sans faille.",
                "Il s'entraîne en percutant des falaises jusqu'à les pulvériser."
                        + " Il préfère finir ses combats par un enchaînement de"
                        + " directs foudroyants.",
                "Frapphino.png", "#4D240B");
        addJavamon("Surivolt", "Acier/Electrique", 65, 110, 75, 95, 90, 120,
                "Un suricate cyborg dont la colonne vertébrale est équipée de"
                        + " bobines Tesla. Ses oreilles captent les signaux électriques"
                        + " à des kilomètres à la ronde.",
                "Créé pour surveiller les centrales électriques, Surivolt a"
                        + " fini par retourner à l'état sauvage. Il se recharge au"
                        + " sommet des collines pendant les orages.",
                "Surivolt.png", "#A6988A");
    }

    private void addJavamon(
            String name, String type,
            int hp, int atk, int def,
            int spAtk, int spDef, int speed,
            String desc, String lore,
            String img, String color) {
        pokedex.put(name, new Javamon(
                name, type, hp, atk, def,
                spAtk, spDef, speed,
                desc, lore, img, color
        ));
    }
}