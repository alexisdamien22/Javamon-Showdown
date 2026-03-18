-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20251117.dfcf3dd949
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 16, 2026 at 09:41 PM
-- Server version: 9.2.0
-- PHP Version: 8.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pokemonshowdown`
--

-- --------------------------------------------------------

--
-- Table structure for table `attacks`
--

CREATE TABLE `attacks` (
  `Id` int NOT NULL,
  `NameFR` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TypeId` int NOT NULL,
  `PP` int NOT NULL,
  `Class` varchar(8) NOT NULL,
  `Power` int DEFAULT NULL,
  `Precision` int DEFAULT NULL,
  `NameEN` varchar(64) NOT NULL,
  `PokemonId` int NOT NULL,
  `priority` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `attacks`
--

INSERT INTO `attacks` (`Id`, `NameFR`, `Description`, `TypeId`, `PP`, `Class`, `Power`, `Precision`, `NameEN`, `PokemonId`, `priority`) VALUES
(61, 'Cascade', 'Charge rapide permettant de franchir des cascades.', 2, 15, 'Phys.', 80, 100, 'Waterfall', 8, 0),
(62, 'Blizzard', 'Déclenche une tempête pouvant geler l\'ennemi.', 5, 5, 'Spéc.', 120, 70, 'Blizzard', 8, 0),
(63, 'Éclats Glace', 'Attaque en premier dans le tour (priorité +1).', 5, 30, 'Phys.', 40, 100, 'Ice Shard', 8, 1),
(64, 'Hydrocanon', 'Envoie un puissant jet d\'eau pour frapper l\'ennemi.', 2, 5, 'Spéc.', 120, 80, 'Hydro Pump', 8, 0),
(65, 'Aqua-Jet', 'Attaque toujours en premier (priorité +1).', 2, 20, 'Phys.', 40, 100, 'Aqua Jet', 8, 1),
(66, 'Charge', 'Charge l\'ennemi avec un violent plaquage.', 0, 35, 'Phys.', 50, 100, 'Tackle', 8, 0),
(67, 'Draco Meteor', 'Diminue beaucoup l\'attaque spéciale du lanceur.', 14, 5, 'Spéc.', 140, 90, 'Draco Meteor', 1, 0),
(68, 'Griffe', 'Lacère l\'ennemi avec des griffes acérées.', 0, 35, 'Phys.', 40, 100, 'Scratch', 1, 0),
(69, 'Crocs Givre', 'Peut geler et apeurer l\'adversaire.', 5, 15, 'Phys.', 65, 95, 'Ice Fang', 1, 0),
(70, 'Dracosouffle', 'Frappe l\'ennemi grâce à un souffle super puissant.', 14, 20, 'Spéc.', 60, 100, 'Dragonbreath', 1, 0),
(71, 'Ouragan', 'Déclenche un terrible ouragan blessant l\'ennemi.', 14, 20, 'Spéc.', 40, 100, 'Twister', 1, 0),
(72, 'Laser Glace', 'Envoie un rayon de glace pouvant geler l\'ennemi.', 5, 10, 'Spéc.', 95, 100, 'Ice Beam', 1, 0),
(73, 'Coupe', 'Coupe l\'ennemi avec des lames, des griffes, etc.', 0, 30, 'Phys.', 50, 95, 'Cut', 2, 0),
(74, 'Lame-Feuille', 'Tranche avec une feuille. Taux de critiques élevé.', 3, 15, 'Phys.', 90, 100, 'Leaf Blade', 2, 0),
(75, 'Tranch\'Herbe', 'Tranche avec des feuilles. Taux de critiques élevé.', 3, 25, 'Phys.', 55, 95, 'Razor Leaf', 2, 0),
(76, 'Groz\'Yeux', 'Baisse d\'un niveau la Défense de l\'adversaire.', 0, 30, 'Autre', NULL, 100, 'Leer', 2, 0),
(77, 'Feuillemagik', 'Envoie des feuilles ne pouvant être esquivées.', 3, 20, 'Spéc.', 60, NULL, 'Magical Leaf', 2, 0),
(78, 'Vampigraine', 'Plante des graines pour voler des PV à chaque tour.', 3, 10, 'Autre', NULL, 90, 'Leech Seed', 2, 0),
(79, 'Picots', 'Pointes blessant lors d\'un changement d\'adversaire.', 8, 20, 'Autre', NULL, NULL, 'Spikes', 3, 0),
(80, 'Tir De Boue', 'Envoie de la boue pour réduire la VITESSE.', 8, 15, 'Spéc.', 55, 95, 'Mud Shot', 3, 0),
(81, 'Force', 'Accumule de la puissance puis frappe l\'ennemi.', 0, 15, 'Phys.', 80, 100, 'Strength', 3, 0),
(82, 'Piétisol', 'Dégâts de zone et baisse la Vitesse.', 8, 20, 'Phys.', 60, 100, 'Bulldoze', 3, 0),
(83, 'Séisme', 'Tremblement de terre (sans effet sur les volants).', 8, 10, 'Phys.', 100, 100, 'Earthquake', 3, 0),
(84, 'Jet De Sable', 'Lance du sable et baisse la précision de l\'ennemi.', 8, 15, 'Autre', NULL, 100, 'Sand-Attack', 3, 0),
(85, 'Déflagration', 'Explosion qui peut brûler l\'ennemi.', 1, 5, 'Spéc.', 120, 85, 'Fire Blast', 4, 0),
(86, 'Danse du Feu', 'Enveloppe de flammes. Peut monter l\'Atk Spé.', 1, 10, 'Spéc.', 80, 100, 'Fiery Dance', 4, 0),
(87, 'Vive-Attaque', 'Attaque fulgurante pour frapper d\'abord.', 0, 30, 'Phys.', 40, 100, 'Quick Attack', 4, 1),
(88, 'Roue De Feu', 'Charge enflammée qui peut brûler l\'ennemi.', 1, 25, 'Phys.', 60, 100, 'Flame Wheel', 4, 0),
(89, 'Léchouille', 'Coup de langue. Peut paralyser.', 13, 30, 'Phys.', 20, 100, 'Lick', 4, 0),
(90, 'Châtiment', 'Dégâts doublés si l\'ennemi a un statut.', 13, 10, 'Spéc.', 50, 100, 'Hex', 4, 0),
(91, 'Bomb-Beurk', 'Détritus pour blesser. Peut empoisonner.', 7, 10, 'Spéc.', 90, 100, 'Sludge Bomb', 5, 0),
(92, 'Plaie-Croix', 'Attaque croisée de type Insecte.', 11, 15, 'Phys.', 80, 100, 'X-scissor', 5, 0),
(93, 'Sécrétion', 'Ligote l\'ennemi pour réduire sa VITESSE.', 11, 40, 'Autre', NULL, 95, 'String Shot', 5, 0),
(94, 'Crochetvenin', 'Morsure pouvant gravement empoisonner.', 7, 15, 'Phys.', 50, 100, 'Poison Fang', 5, 0),
(95, 'Survinsecte', 'Résiste et attaque. Baisse l\'Atk Spé de l\'ennemi.', 11, 20, 'Spéc.', 30, 100, 'Struggle Bug', 5, 0),
(96, 'Abri', 'Esquive l\'attaque (peut échouer si répété).', 0, 10, 'Autre', NULL, 100, 'Protect', 5, 4),
(97, 'Lame d\'Air', '30% de chance d\'apeurer l\'ennemi.', 9, 20, 'Spéc.', 75, 95, 'Air Slash', 6, 0),
(98, 'Explonuit', 'Onde ténébreuse. Peut baisser la Précision.', 15, 10, 'Spéc.', 85, 95, 'Night Daze', 6, 0),
(99, 'Bélier', 'Charge violente avec dégâts de recul.', 0, 20, 'Phys.', 90, 85, 'Take Down', 6, 0),
(100, 'Vibrobscur', '20% de chance d\'apeurer l\'adversaire.', 15, 15, 'Spéc.', 80, 100, 'Dark Pulse', 6, 0),
(101, 'Représailles', 'Puissance double si frappé en deuxième.', 15, 10, 'Phys.', 50, 100, 'Payback', 6, -4),
(102, 'Patience', 'Encaisse 2 tours et renvoie le double.', 0, 10, 'Phys.', NULL, 100, 'Bide', 6, 0),
(103, 'Frappe Psy', 'Ondes mystérieuses. Dégâts physiques.', 10, 10, 'Spéc.', 100, 100, 'Psystrike', 7, 0),
(104, 'Lumi-Éclat', 'Lueur intense. Peut baisser la DEF. SPE.', 10, 5, 'Spéc.', 70, 100, 'Luster Purge', 7, 0),
(105, 'Bluff', 'Frappe au 1er tour pour apeurer.', 0, 10, 'Phys.', 40, 100, 'Fake Out', 7, 3),
(106, 'Psykoud\'Boul', '20% de chance d\'apeurer.', 10, 15, 'Phys.', 80, 90, 'Zen Headbutt', 7, 0),
(107, 'Télékinésie', 'Diminue la précision de l\'adversaire.', 10, 15, 'Autre', NULL, 80, 'Kinesis', 7, 0),
(108, 'Vive-Attaque', 'Attaque fulgurante pour frapper d\'abord.', 0, 30, 'Phys.', 40, 100, 'Quick Attack', 7, 1),
(109, 'Close Combat', 'Baisse la Défense et Déf. Spé du lanceur.', 6, 5, 'Phys.', 120, 100, 'Close Combat', 9, 0),
(110, 'Double Pied', 'Attaque deux fois en un seul tour.', 6, 30, 'Phys.', 30, 100, 'Double Kick', 9, 0),
(111, 'Écras\'Face', 'Écrase l\'ennemi avec les pattes ou la queue.', 0, 35, 'Phys.', 40, 100, 'Pound', 9, 0),
(112, 'Éclate-Roc', '50% de chance de baisser la DÉFENSE.', 6, 15, 'Phys.', 40, 100, 'Rock Smash', 9, 0),
(113, 'Empal\'Korne', 'Coup de corne. Met K.O. en un coup.', 0, 5, 'Phys.', NULL, 30, 'Horn Drill', 9, 0),
(114, 'Rengorgement', 'Augmente l\'Attaque et l\'Attaque Spéciale.', 0, 30, 'Autre', NULL, NULL, 'Work Up', 9, 0),
(115, 'Éclair Fou', 'Charge électrique avec dégâts de recul.', 4, 15, 'Phys.', 90, 100, 'Wild Charge', 10, 0),
(116, 'Éclair', 'Électricité pouvant paralyser l\'ennemi.', 4, 30, 'Spéc.', 40, 100, 'Thundershock', 10, 0),
(117, 'Toile Elek', 'Filet électrique. Baisse la Vitesse.', 4, 15, 'Spéc.', 55, 95, 'Electroweb', 10, 0),
(118, 'Cage-Éclair', 'Faible choc qui paralyse l\'ennemi.', 4, 20, 'Autre', NULL, 100, 'Thunder Wave', 10, 0),
(119, 'Onde De Choc', 'Rapide et impossible à esquiver.', 4, 20, 'Spéc.', 60, NULL, 'Shock Wave', 10, 0),
(120, 'Luminocanon', 'Peut diminuer la défense spéciale.', 16, 10, 'Spéc.', 80, 100, 'Flash Cannon', 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pokemons`
--

CREATE TABLE `pokemons` (
  `Id` int NOT NULL,
  `Name` varchar(256) NOT NULL,
  `HP` int NOT NULL,
  `Atk` int NOT NULL,
  `Def` int NOT NULL,
  `AtkSpe` int NOT NULL,
  `DefSpe` int NOT NULL,
  `Speed` int NOT NULL,
  `Description` text NOT NULL,
  `Lore` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pokemons`
--

INSERT INTO `pokemons` (`Id`, `Name`, `HP`, `Atk`, `Def`, `AtkSpe`, `DefSpe`, `Speed`, `Description`, `Lore`) VALUES
(1, 'Crystalion', 60, 55, 65, 115, 70, 110, 'On dit que si l\'un de ses éclats tombe, il repousse en une semaine. Les mineurs le suivent souvent, car sa présence indique la proximité de gisements rares.', 'Ce petit dragon vit dans les grottes de cristal les plus profondes. Son corps est composé de gemmes semi-précieuses qui emmagasinent la lumière pour la rejeter sous forme de rayons laser dévastateurs.'),
(2, 'Roncigami', 75, 90, 130, 50, 95, 40, 'Il est né d\'une légende racontant qu\'un maître en origami aurait insufflé la vie à ses créations pour protéger son jardin des envahisseurs. Il ne flétrit jamais, même en plein hiver.', 'Un Pokémon composé de feuilles rigides pliées comme du papier. Ses bords sont aussi tranchants que des lames de rasoir, et il se déplace avec une rigidité mécanique.'),
(3, 'Towltem', 100, 60, 85, 105, 140, 10, 'On raconte que Towltem peut ralentir les battements de cœur de ses adversaires en inversant le sable de son sablier. Il est le gardien des ruines oubliées par l\'histoire.', 'Un automate massif dont le buste contient un sablier magique. Il ne bouge presque jamais, mais ses yeux brillent intensément quand le temps s\'écoule.'),
(4, 'Rubrasier', 50, 120, 45, 100, 45, 145, 'Il est capable de traverser les murs en les faisant fondre instantanément. Apercevoir un Rubrasier dans le ciel nocturne est un signe de canicule imminente.', 'Un serpent de pure énergie thermique qui ondule dans les airs comme un ruban de soie en feu. Il ne touche jamais le sol.'),
(5, 'Moustix', 70, 95, 80, 95, 80, 85, 'Ce Pokémon prospère dans les milieux pollués. Les orbes verts sur son dos contiennent un venin capable de paralyser un Rhino en quelques secondes.', 'Un insecte bourdonnant entouré d\'un nuage de spores toxiques. Ses yeux multiples lui permettent de détecter les faiblesses biologiques de ses proies.'),
(6, 'Orbitacle', 85, 40, 80, 125, 110, 75, 'On pense que Orbitacle est tombé des étoiles il y a des millénaires. Il communique avec ses congénères par ondes radio que les humains captent parfois comme des sifflements.', 'Une entité aquatique dont la tête ressemble à une planète entourée d\'anneaux de débris spatiaux. Ses tentacules flottent comme s\'ils étaient en apesanteur.'),
(7, 'Marietta', 65, 45, 70, 105, 125, 80, 'Bien qu\'elle ait l\'air inoffensive, c\'est elle qui contrôle la main spectrale et non l\'inverse. Elle adore jouer des tours aux voyageurs pour les égarer dans la brume.', 'Une marionnette rose aux yeux de bouton qui semble tenue par des fils invisibles reliés à une main fantomatique flottant au-dessus d\'elle.'),
(8, 'Frimanta', 80, 70, 75, 110, 90, 105, 'Elle survole les océans arctiques en silence. On raconte que son chant peut geler une vague entière, créant ainsi des ponts de glace pour les Pokémon terrestres.', 'Une raie majestueuse faite de glace éternelle. Ses ailes cristallines scintillent et laissent derrière elles une traînée de poudreuse givrée.'),
(9, 'Frapphino', 115, 140, 110, 20, 65, 60, 'Il s\'entraîne en percutant des falaises jusqu\'à les pulvériser. Sa corne n\'est pas son arme principale : il préfère finir ses combats par un enchaînement de directs foudroyants.', 'Un rhinocéros bipède massif portant des gants de boxe naturels et une ceinture de champion. Son regard exprime une détermination sans faille.'),
(10, 'Surivolt', 65, 110, 75, 95, 90, 120, 'Créé par un ingénieur de génie pour surveiller les centrales électriques, Surivolt a fini par retourner à l\'état sauvage. Il se recharge en se plaçant au sommet des collines pendant les orages.', 'Un suricate cyborg dont la colonne vertébrale est équipée de bobines Tesla. Ses oreilles captent les signaux électriques à des kilomètres à la ronde.');

-- --------------------------------------------------------

--
-- Table structure for table `pokemontypes`
--

CREATE TABLE `pokemontypes` (
  `PokemonId` int NOT NULL,
  `TypeId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pokemontypes`
--

INSERT INTO `pokemontypes` (`PokemonId`, `TypeId`) VALUES
(1, 14),
(1, 5),
(2, 3),
(2, 0),
(3, 8),
(4, 1),
(4, 13),
(5, 11),
(5, 7),
(6, 9),
(6, 15),
(7, 17),
(8, 5),
(8, 2),
(9, 6),
(10, 16),
(10, 4);

-- --------------------------------------------------------

--
-- Table structure for table `typeeffectiveness`
--

CREATE TABLE `typeeffectiveness` (
  `attackTypeId` int NOT NULL,
  `defenderTypeId` int NOT NULL,
  `multiplier` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `typeeffectiveness`
--

INSERT INTO `typeeffectiveness` (`attackTypeId`, `defenderTypeId`, `multiplier`) VALUES
(0, 12, 0.5),
(0, 13, 0),
(0, 16, 0.5),
(1, 3, 2),
(1, 5, 2),
(1, 11, 2),
(1, 2, 0.5),
(1, 12, 0.5),
(1, 16, 0.5),
(2, 1, 2),
(2, 8, 2),
(2, 12, 0.5),
(2, 3, 0.5),
(2, 5, 0.5),
(3, 1, 0.5),
(3, 2, 2),
(3, 5, 0.5),
(3, 7, 0.5),
(3, 8, 2),
(3, 9, 0.5),
(3, 12, 2),
(4, 2, 2),
(4, 8, 0),
(4, 9, 2),
(4, 16, 0.5),
(5, 3, 2),
(5, 8, 2),
(5, 9, 2),
(5, 14, 2),
(5, 1, 0.5),
(5, 2, 0.5),
(5, 12, 0.5),
(5, 16, 0.5),
(6, 0, 2),
(6, 5, 2),
(6, 12, 2),
(6, 13, 0),
(6, 14, 0.5),
(6, 15, 2),
(6, 16, 2),
(6, 17, 0.5),
(7, 3, 2),
(7, 8, 0.5),
(7, 12, 0.5),
(7, 13, 0),
(7, 16, 0.5),
(7, 17, 2),
(8, 1, 2),
(8, 4, 2),
(8, 7, 2),
(8, 9, 0),
(8, 11, 0.5),
(8, 16, 2),
(9, 3, 2),
(9, 6, 0.5),
(9, 11, 2),
(9, 12, 0.5),
(10, 6, 2),
(10, 7, 2),
(10, 15, 0.5),
(10, 13, 0.5),
(11, 1, 0.5),
(11, 3, 0.5),
(11, 6, 2),
(11, 10, 0.5),
(11, 12, 0.5),
(11, 15, 2),
(11, 17, 0.5),
(12, 1, 2),
(12, 5, 2),
(12, 9, 2),
(12, 11, 0.5),
(12, 6, 0.5),
(12, 8, 0.5),
(13, 0, 0),
(13, 10, 2),
(13, 13, 2),
(13, 15, 0.5),
(14, 14, 2),
(14, 17, 0),
(15, 10, 2),
(15, 13, 2),
(15, 11, 0.5),
(15, 17, 0.5),
(16, 5, 2),
(16, 11, 0.5),
(16, 12, 2),
(16, 7, 0.5),
(16, 16, 0.5),
(16, 17, 2),
(17, 6, 2),
(17, 14, 2),
(17, 15, 0.5),
(17, 16, 0.5);

-- --------------------------------------------------------

--
-- Table structure for table `typelevels`
--

CREATE TABLE `typelevels` (
  `AttackTypeId` int NOT NULL,
  `DefenseTypeId` int NOT NULL,
  `WeaknessType` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `typelevels`
--

INSERT INTO `typelevels` (`AttackTypeId`, `DefenseTypeId`, `WeaknessType`) VALUES
(0, 12, 1),
(0, 13, 0),
(0, 16, 1),
(1, 3, 2),
(1, 5, 2),
(1, 11, 2),
(1, 2, 1),
(1, 12, 1),
(1, 16, 1),
(2, 1, 2),
(2, 8, 2),
(2, 12, 1),
(2, 3, 1),
(2, 5, 1),
(3, 1, 1),
(3, 2, 2),
(3, 5, 1),
(3, 7, 1),
(3, 8, 2),
(3, 9, 1),
(3, 12, 2),
(4, 2, 2),
(4, 8, 0),
(4, 9, 2),
(4, 16, 1),
(5, 3, 2),
(5, 8, 2),
(5, 9, 2),
(5, 14, 2),
(5, 1, 1),
(5, 2, 1),
(5, 12, 1),
(5, 16, 1),
(6, 0, 2),
(6, 5, 2),
(6, 12, 2),
(6, 13, 0),
(6, 14, 1),
(6, 15, 2),
(6, 16, 2),
(6, 17, 1),
(7, 3, 2),
(7, 8, 1),
(7, 12, 1),
(7, 13, 0),
(7, 16, 1),
(7, 17, 2),
(8, 1, 2),
(8, 4, 2),
(8, 7, 2),
(8, 9, 0),
(8, 11, 1),
(8, 16, 2),
(9, 3, 2),
(9, 6, 1),
(9, 11, 2),
(9, 12, 1),
(10, 6, 2),
(10, 7, 2),
(10, 15, 1),
(10, 13, 1),
(11, 1, 1),
(11, 3, 1),
(11, 6, 2),
(11, 10, 1),
(11, 12, 1),
(11, 15, 2),
(11, 17, 1),
(12, 1, 2),
(12, 5, 2),
(12, 9, 2),
(12, 11, 1),
(12, 6, 1),
(12, 8, 1),
(13, 0, 0),
(13, 10, 2),
(13, 13, 2),
(13, 15, 1),
(14, 14, 2),
(14, 17, 0),
(15, 10, 2),
(15, 13, 2),
(15, 11, 1),
(15, 17, 1),
(16, 5, 2),
(16, 11, 1),
(16, 12, 2),
(16, 7, 1),
(16, 16, 1),
(16, 17, 2),
(17, 6, 2),
(17, 14, 2),
(17, 15, 1),
(17, 16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

CREATE TABLE `types` (
  `Id` int NOT NULL,
  `Name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`Id`, `Name`) VALUES
(0, 'Normal'),
(1, 'Fire'),
(2, 'Water'),
(3, 'Grass'),
(4, 'Electric'),
(5, 'Ice'),
(6, 'Fighting'),
(7, 'Poison'),
(8, 'Ground'),
(9, 'Flying'),
(10, 'Psychic'),
(11, 'Bug'),
(12, 'Rock'),
(13, 'Ghost'),
(14, 'Dragon'),
(15, 'Dark'),
(16, 'Steel'),
(17, 'Fairy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attacks`
--
ALTER TABLE `attacks`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `pokemons`
--
ALTER TABLE `pokemons`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `types`
--
ALTER TABLE `types`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attacks`
--
ALTER TABLE `attacks`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `pokemons`
--
ALTER TABLE `pokemons`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
