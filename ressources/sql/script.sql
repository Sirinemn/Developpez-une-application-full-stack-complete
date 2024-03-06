-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 07 mars 2024 à 00:17
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet6`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `article_id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`article_id`, `content`, `created_at`, `title`, `updated_at`, `topic_id`, `user_id`) VALUES
(1, 'On October 10, 2011, researchers from the University of Minnesota found that women who took supplemental multivitamins died at rates higher than those who didn’t. Two days later, researchers from the Cleveland Clinic found that men who took vitamin E had ', '2024-02-17 22:44:44.000000', 'The Vitamin Myth: Why We Think We Need Supplements', NULL, 3, 3),
(2, 'In 1931, Linus Pauling published a paper in the Journal of the American Chemical Society titled “The Nature of the Chemical Bond.” Before its publication, chemists knew of two types of chemical bonds: ionic, where one atom gives up an electron to another,', '2024-02-17 22:44:56.000000', 'Death of the calorie', NULL, 3, 3),
(3, 'Regular exercise all year long is best for your health and wellness. Particularly in the winter — when you’re maybe more likely to experience a drop in mood — staying active can help improve your mental health and energy, too.\r\n\r\nBut is it a bad idea to e', '2024-02-18 13:35:11.000000', 'Cold Weather Workouts: What to Wear and When You’re Better Off Staying Inside', '2024-02-18 13:35:11.000000', 2, 3),
(4, 'taking very suggary food in your breakfast is very dangerous, it makes you feel very hungry by 10am', '2024-02-26 14:30:49.000000', 'Stop taking sweets in your breakfast', '2024-02-26 14:30:49.000000', 2, 3),
(5, 'Un muffin contient les meme calorie qu\'un 1Kg de fraise entier', '2024-02-26 15:09:07.000000', 'Calculer les colories de votre plat', '2024-02-26 15:09:07.000000', 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
  `comment_id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `comments`
--

INSERT INTO `comments` (`comment_id`, `content`, `article_id`, `user_id`) VALUES
(1, 'very good', 2, 3),
(2, 'ceci est un commentaire', 2, 3),
(3, 'c\'est très intéressant ', 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `subscribe`
--

CREATE TABLE `subscribe` (
  `user_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `subscribe`
--

INSERT INTO `subscribe` (`user_id`, `topic_id`) VALUES
(3, 1),
(3, 4),
(3, 6);

-- --------------------------------------------------------

--
-- Structure de la table `topics`
--

CREATE TABLE `topics` (
  `topic_id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `topics`
--

INSERT INTO `topics` (`topic_id`, `name`, `description`) VALUES
(1, 'TRAVEL', 'Travel and tourism can provide a much-needed break from the stresses of everyday life. Writing about destinations, cultures, and unique experiences can be fun and informative. Some topics to write about:\r\n\r\n· Destination Guides\r\n\r\n· Travel Tips and Tricks'),
(2, 'SPORT', 'Sports and entertainment provide a fun and exciting way to connect with others and unwind. Writing about these topics can provide insights into popular culture and entertainment news, as well as offer tips and tricks for casual or serious sports enthusias'),
(3, 'FOOD', 'Food and drink topics can range from recipes to culinary trends to the science behind what we eat and drink. You can suggest some diet foods and drinks as well. Writing about these topics can inspire and educate foodies and casual cooks alike. You can wri'),
(4, 'TECHNOLOGIE', 'With new advancements every day, technology is an ever-evolving field with endless possibilities. Writing about technology and gadgets can range from breaking news to in-depth analysis of new products or emerging trends. There is a never-ending range of t'),
(5, 'ENVIRONNEMENT', 'The environment and sustainability are increasingly important topics in the modern world. Writing about these topics can help raise awareness and provide solutions for a healthier planet. These are very specific topics so only people interested in climate'),
(6, 'FINANCE', 'Business and finance are integral to modern society, impacting individuals and larger organizations. Writing on this topic can range from financial planning to exploring the latest business trends.\r\n\r\n· Entrepreneurship\r\n\r\n· Personal Finance\r\n\r\n· Investin'),
(7, 'HEALTH', 'Health and wellness are among the most in-demand topics, as taking care of one’s physical, mental, and emotional health is crucial for a happy and fulfilling life. Topics related to health and wellness can range from fitness to mindfulness to nutrition. W');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `created_at`, `email`, `password`, `updated_at`, `name`) VALUES
(1, '2024-02-08 22:43:32.000000', 'test@test.fr', '$2a$10$avq.dskrd75GG.6zhpOJ5OZ6SN6ldpmygmndGhRiFOCb3QCcJsg26', '2024-02-16 10:03:28.000000', 'test'),
(2, '2024-02-08 22:43:58.000000', 'test@test.fr', '$2a$10$f70HgAtY.zeaFGaM0IsuUOKNrph44qa4A9E7ZoGc.oi.mc5Czesxm', '2024-02-16 10:05:16.000000', 'test1'),
(3, '2024-02-08 22:44:09.000000', 'mdd@mail.fr', '$2a$10$wHQ4.9z7BaQVn5/fYcTrtu9i2BLbotHU7srxZsQboKG6xiRbPoGSi', '2024-03-06 23:04:33.000000', 'mango'),
(4, '2024-02-08 22:44:20.000000', 'user@mail.fr', '$2a$10$j3xzxTN0a6HJ5d3ucX3yee2u3CIzwmxlEMqg6DQERsFEafrz9IuJy', '2024-02-18 14:17:09.000000', 'user'),
(5, NULL, 'back@works.fr', '$2a$10$WW0hH8WmTDTLrb9CKfhc8uvc/M8vpUcD44e7iIcZMCRWA7B00eEmy', '2024-02-20 14:46:08.000000', 'back'),
(6, NULL, 'front@works.fr', '$2a$10$gIrmZTAcuVa5tLh5/sabmeJBPbchBwlZNrDsxCRx4K2p.MGs..4ta', '2024-02-20 15:02:09.000000', 'front');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(3, 1),
(4, 1),
(5, 1),
(6, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`article_id`),
  ADD KEY `FKtr90v51q71w7rpslscsfjf3cv` (`topic_id`),
  ADD KEY `FKlc3sm3utetrj1sx4v9ahwopnr` (`user_id`);

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FKk4ib6syde10dalk7r7xdl0m5p` (`article_id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Index pour la table `subscribe`
--
ALTER TABLE `subscribe`
  ADD PRIMARY KEY (`user_id`,`topic_id`),
  ADD KEY `FKfa7ldsooda5rpksco382vi9e4` (`topic_id`);

--
-- Index pour la table `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`topic_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  ADD KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `article_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `topics`
--
ALTER TABLE `topics`
  MODIFY `topic_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `FKlc3sm3utetrj1sx4v9ahwopnr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKtr90v51q71w7rpslscsfjf3cv` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`);

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKk4ib6syde10dalk7r7xdl0m5p` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`);

--
-- Contraintes pour la table `subscribe`
--
ALTER TABLE `subscribe`
  ADD CONSTRAINT `FKfa7ldsooda5rpksco382vi9e4` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  ADD CONSTRAINT `FKgnxvys06tns02nbr5bxj1xxh8` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
