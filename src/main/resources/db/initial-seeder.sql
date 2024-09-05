INSERT INTO
    formats (id, name)
VALUES
   (1, "presencial"),
   (2, "remoto"),
   (3, "híbrido")
;


INSERT INTO
    levels (id, name)
VALUES
    (1, "fundamental"),
    (2, "médio"),
    (3, "técnico"),
    (4, "graduação"),
    (5, "pós")
;


INSERT INTO
    periods (id, name)
VALUES
    (1, "matutino"),
    (2, "vespertino"),
    (3, "noturno")
;


INSERT INTO
    mod_status (id, name)
VALUES
    (1, "aprovado"),
    (2, "rejeitado")
;


INSERT INTO `areas` (`name`) VALUES
('Ciências da Computação'),
('Administração de Empresas'),
('Engenharia de Software'),
('Matemática Aplicada'),
('Psicologia'),
('Economia'),
('Design Gráfico'),
('Comunicação Social'),
('Engenharia de Produção'),
('Biotecnologia');


// CRIA ORG COM ID 1, MAS CRIA PELO SITE!
INSERT INTO `orgs` (
    `cnpj`, 
    `created_at`, 
    `ie`, 
    `info`, 
    `nome`, 
    `redes_sociais`, 
    `updated_at`, 
    `website`
) VALUES (
    '00.000.000/0001-00',  -- Exemplo de CNPJ fictício
    NOW(), 
    0,  -- Valor de bit(1) indicando 'false' para 'ie'
    'A Nobanks é uma organização verdadeiramente inovadora que tem como missão não oferecer nenhum tipo de serviço bancário. Isso mesmo, ao contrário de todos os bancos, a Nobanks se orgulha de não ter contas, cartões, ou qualquer produto financeiro.',
    'Nobanks', 
    'twitter.com/nobanks | facebook.com/nobanks', 
    NOW(), 
    'www.nobanks.com'
);

INSERT INTO 
	addresses 
VALUES 
	("main", 1, "Centro", "Florianópolis", "2024-06-10 10:53:35", "SC", "BRA", "Avenida Hercílio Luz, 520", "2024-06-10 10:53:35", 1);


INSERT INTO 
	contacts 
VALUES 
	("main", 1, "nobanks@nobanks.com", "48 3253-6532", "2024-06-10 10:53:36", 1), 
	("appliance", 2, "rh@nobanks.com", "48 3256-6633", "2024-06-10 10:53:37", 1);



-- Inserir registros na tabela de vagas de estágio
INSERT INTO `jobs` (
    `carga_horaria_semanal`, 
    `created_at`, 
    `data_final`, 
    `data_inicio`, 
    `descricao`, 
    `duracao_meses`, 
    `format_id`, 
    `imagem`, 
    `study_level_id`, 
    `period_id`, 
    `remuneracao`, 
    `requisitos`, 
    `titulo`, 
    `updated_at`, 
    `address_id`, 
    `contact_id`, 
    `owner_id`
) VALUES
(
    20, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 6 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário será responsável por auxiliar na análise de sistemas e desenvolvimento de software. Deverá ter conhecimento básico em programação e trabalhar em equipe para resolver problemas técnicos. A vaga oferece uma ótima oportunidade para aprender e crescer na área de TI. O candidato ideal deve ser proativo e ter boa comunicação. O estágio será realizado no escritório central.',
    6, 
    1, 
    'imagem1.jpg', 
    2, 
    1, 
    1200.00, 
    'Conhecimento básico em programação, boa comunicação e trabalho em equipe.', 
    'Estágio em Desenvolvimento de Software', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    30, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 3 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário auxiliará nas atividades administrativas diárias e no planejamento estratégico. É essencial ter habilidades organizacionais e ser capaz de lidar com vários projetos ao mesmo tempo. A vaga proporciona experiência prática em um ambiente corporativo e oportunidades para desenvolvimento profissional. Procuramos alguém motivado e com boa capacidade de análise.',
    3, 
    2, 
    'imagem2.jpg', 
    3, 
    2, 
    800.00, 
    'Habilidades organizacionais e capacidade de gestão de múltiplas tarefas.', 
    'Estágio em Administração de Empresas', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    40, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 12 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'Atividades incluem suporte na criação e gestão de software, bem como análise de requisitos. O estagiário trabalhará diretamente com a equipe de desenvolvimento para implementar novas funcionalidades. É necessário conhecimento avançado em programação e vontade de aprender sobre novas tecnologias. A experiência adquirida será valiosa para futuros projetos profissionais.',
    12, 
    3, 
    'imagem3.jpg', 
    4, 
    3, 
    1500.00, 
    'Conhecimento avançado em programação e interesse em desenvolvimento de software.', 
    'Estágio em Engenharia de Software', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    10, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 4 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário ajudará na aplicação de métodos estatísticos e análise de dados. A posição é ideal para quem deseja adquirir experiência em técnicas matemáticas aplicadas a problemas reais. Espera-se que o candidato tenha um bom conhecimento em matemática e estatística, além de habilidades analíticas e de resolução de problemas.',
    4, 
    1, 
    'imagem4.jpg', 
    1, 
    1, 
    1000.00, 
    'Conhecimento em matemática e estatística, habilidades analíticas.', 
    'Estágio em Matemática Aplicada', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    20, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 5 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário vai auxiliar em atividades de pesquisa e desenvolvimento psicológico. A vaga é destinada para quem está interessado em áreas relacionadas ao comportamento humano e deseja trabalhar em um ambiente acadêmico ou clínico. Experiência anterior em pesquisa é um diferencial.',
    5, 
    2, 
    'imagem5.jpg', 
    2, 
    2, 
    900.00, 
    'Interesse em pesquisa psicológica e conhecimento básico em psicologia.', 
    'Estágio em Psicologia', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    30, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 8 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'Auxílio na análise econômica e suporte em projetos financeiros. O estagiário participará de estudos e relatórios econômicos e ajudará a monitorar tendências de mercado. É desejável ter conhecimento em economia e habilidades analíticas. O estágio oferece uma valiosa experiência em um ambiente financeiro dinâmico.',
    8, 
    3, 
    'imagem6.jpg', 
    3, 
    1, 
    1200.00, 
    'Conhecimento básico em economia e habilidades analíticas.', 
    'Estágio em Economia', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    40, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 9 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário vai colaborar com a equipe de design gráfico para criar materiais visuais e campanhas publicitárias. Será necessário conhecimento em ferramentas de design e criatividade. A vaga oferece a chance de trabalhar em projetos variados e de impactar a comunicação visual da empresa.',
    9, 
    3, 
    'imagem7.jpg', 
    4, 
    3, 
    1500.00, 
    'Conhecimento em design gráfico e habilidades criativas.', 
    'Estágio em Design Gráfico', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    20, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 6 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário auxiliará na criação de conteúdos e estratégias de comunicação. A vaga é voltada para quem tem interesse em mídia e comunicação e deseja aprender sobre produção de conteúdo e gerenciamento de campanhas. A experiência adquirida será útil para quem pretende seguir carreira na área de comunicação.',
    6, 
    2, 
    'imagem8.jpg', 
    2, 
    2, 
    800.00, 
    'Habilidades em escrita e interesse em comunicação social.', 
    'Estágio em Comunicação Social', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    30, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 7 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário ajudará na coordenação e gestão de projetos de produção. Deverá ter conhecimento em processos de engenharia e habilidades organizacionais para auxiliar na supervisão de processos produtivos. O estágio oferece uma experiência prática em um ambiente dinâmico de produção.',
    7, 
    3, 
    'imagem9.jpg', 
    3, 
    1, 
    1300.00, 
    'Conhecimento em engenharia e habilidades organizacionais.', 
    'Estágio em Engenharia de Produção', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
),
(
    10, 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    DATE_ADD(NOW() - INTERVAL FLOOR(RAND() * 10) DAY, INTERVAL 2 MONTH), 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    'O estagiário irá apoiar em projetos relacionados à biotecnologia e ajudará nas tarefas laboratoriais. É essencial ter interesse em ciências biológicas e habilidades básicas em laboratório. O estágio é uma ótima oportunidade para quem quer ingressar na área de biotecnologia e ciências aplicadas.',
    2, 
    1, 
    'imagem10.jpg', 
    1, 
    2, 
    1000.00, 
    'Interesse em biotecnologia e habilidades básicas em laboratório.', 
    'Estágio em Biotecnologia', 
    NOW() - INTERVAL FLOOR(RAND() * 10) DAY, 
    1, 
    1, 
    1
);



INSERT INTO 
	`areas_jobs` (`area_id`, `job_id`) 
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7),
	(8, 8),
	(9, 9),
	(10, 10);



