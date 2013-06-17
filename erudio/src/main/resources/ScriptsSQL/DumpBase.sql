INSERT INTO `cidade` (`IdCidade`,`Nome`) VALUES
 (1,'PATOS DE MINAS'),
 (2,'PATROCÍNIO'),
 (3,'UBERLÂNDIA'),
 (4,'UBERABA'),
 (5,'ARAGUARI'),
 (6,'URUAÇU'),
 (7,'SÃO MIGUEL DO OESTE'),
 (8,'GOIÂNIA');
INSERT INTO `estado` (`IdEstado`,`NomeEstado`) VALUES 
 (1,'ACRE'),
 (2,'ALAGOAS'),
 (3,'AMAZONAS'),
 (4,'AMAPÁ'),
 (5,'BAHIA'),
 (6,'CEARÁ'),
 (7,'DISTRITO FEDERAL'),
 (8,'ESPÍRITO SANTO'),
 (9,'GOIÁS'),
 (10,'MARANHÃO'),
 (11,'MINAS GERAIS'),
 (12,'MATO GROSSO DO SUL'),
 (13,'MATO GROSSO'),
 (14,'PARÁ'),
 (15,'PARAÍBA'),
 (16,'PERNAMBUCO'),
 (17,'PIAUÍ'),
 (18,'PARANÁ'),
 (19,'RIO DE JANEIRO'),
 (20,'RIO GRANDE DO NORTE'),
 (21,'RORAIMA'),
 (22,'RONDÔNIA'),
 (23,'RIO GRANDE DO SUL'),
 (24,'SANTA CATARINA'),
 (25,'SERGIPE'),
 (26,'SÃO PAULO'),
 (27,'TOCANTINS');
INSERT INTO `sexo` (`IdSexo`,`Descricao`) VALUES 
 (1,'MASCULINO'),
 (2,'FEMININO');
INSERT INTO `tipoendereco` (`IdTipoEndereco`,`DescricaoTipoEndereco`) VALUES 
 (1,'RESIDENCIAL'),
 (2,'COMERCIAL'),
 (3,'RODOVIA'),
 (4,'FAZENDA'),
 (5,'INDÚSTRIA'),
 (6,'CHÁCARA'),
 (7,'VILA');
INSERT INTO `tipologradouro` (`IdTipoLogradouro`,`DescricaoTipoLogradouro`) VALUES 
 (1,'RUA'),
 (2,'ALAMEDA'),
 (3,'AVENIDA'),
 (4,'VIELA'),
 (5,'BECO'),
 (6,'TRAVESSA'),
 (7,'PRAÇA');
INSERT INTO `pessoa` (`IdPessoa`,`DataDeNascimentoFundacao`,`DataDeCadastro`,`Email`,`NomeRazaoSocial`,`Telefone`,`IdSexo`,`Login`,`Permissao`,`Senha`,`TipoPessoa`) VALUES
 (1,'2013-01-12','2013-01-12','palger@live.com','LEANDRO DA COSTA GONÇALVES','(11) 1111-1111',1,'admin','ROLE_ADMIN','d033e22ae348aeb5660fc2140aec35850c4da997','F');