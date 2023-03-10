# act-digital-challenge

O Projeto é baseado em um sistema para votação em assembleias. o sistema armazena uma lista de assembleias, cada assembleia pode ter mais de uma pauta, que a principio é adicionada com o status INATIVO, quando o usuario abre a sessão, a pauta muda o status para ATIVO, por padrão ela fica aberta por 60 segundos. o CPF do associado é usado apenas para validar que é uma pessoa fisica com um CPF valido, toda as transações dentro o webservice é feito através do Id, que é um UUID.

Nesta primeira versão, não foram implementados os testes unitários.

A primeira versão está com a TAG 1.0.0, o primeiro digito é o major, para a primeira release, o segundo digito são as novas features entregues na versão e o ultimo digito para correções de bug.

## Arquitetura

A arquitetura do projeto contem um webservice RESTFul em Spring boot com OpenAPI para documentação da API, um banco de dados PostgreSQL, um PgAdmin4 e um serviço de mensageria com RabbitMQ para enfileiar os votos.

### devOps helpers

Para executar o ambiente de teste, é preciso renomear os arquivos **docker-compose_example.yml** para **docker-compose.yml** e escolher as senhas para os serviços de banco de dados e mensageria.

* **POSTGRES_PASSWD** precisa ser igual a **POSTGRES_PASSWORD**
* **QUEUE_PASSWD** precisa ser igual a **RABBITMQ_DEFAULT_PASS**

o docker compose iria subir 4 serviços:

* o service **api**, com o build do container através do Dockerfile dentro do diretório api, configurado com a imagem do openJDK 17, irá realizar download e instalação do maven, clonar o repositorio git do projeto no GitHub, realizar o build e executar o jar.
* o service **db** e **pgadmin** irá subir o banco e a console de adminstração do banco de dados.
* o service **rabbitmq** para subir o serviço de mensageria, com o arquivo de configuração que registra a queue, o exchange e o routing key.

_Obs. No meu ambiente de desenvolvimento, a versão do Windows não permite instalar docker, para resolver isso, eu utilizei o Vagrant, mas se for possível rodar o docker diretamente no ambiente de testes, só precisa executar o docker compose._

_Obs 2. Se for criar uma VM com o Vagrant, é possível rodar o script install-docker.sh (dando a permissão de execução no linux)._

### acessos

<http://endereco-ip:8080/swagger-ui.html> para acessar a documentação da API

<http://endereco-ip:15672> para acessar a console do RabbitMQ

<http://endereco-ip:16543> para acessar o PgAdmin

