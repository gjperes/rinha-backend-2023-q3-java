# rinha-backend-2023-q3-java

Backend pra rinha backend 2023 q3. 
Tentativa de melhoria em cima da implementação base: https://github.com/hugomarques/rinha-backend-2023-q3-java

Mais informações sobre a rinha no Repo: https://github.com/zanfranceschi/rinha-de-backend-2023-q3

## Implementação

Esse repo tem 3 implementações (completas ou não).

A branch spring-tradicional contém uma implementação feijão com arroz:
- Spring Boot
- PostgreSQL
- Uso de Java 17

A branch jdbc contém as seguintes mudanças:
- Spring boot
- Spring Data Jdbc
- Uso de Java 21 também pelo project Loom.

A branch main/flux implementa a solução com o paradigma reativo:
- Spring Boot Webflux
- R2dbc
- Postgresql (e o seu devido driver reativo)
- Redis

## Requisitos

Para rodar, precisamos ter instalado:

* Docker
* Gatling (versão usada [3.9.5](https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.9.5/)


## Para rodar localmente

1. Subir o ambiente (PostgreSQL) com o docker :

Na raiz do projeto, executar:

``  docker compose -f docker-compose-local.yml up  ``

2. Subir a aplicação do Spring Boot (pela IDE ou por terminal), configuração:

``  MAVEN_OPTS="-Xmx1500m --enable-preview"  mvn spring-boot:run ``

3. Executaar os testes do Galting

``  
cd stress-test
./run-test.sh
``

Tudo isso dá para ser feito dentro do IntelliJ também, se preferir.

Importante: A cada teste remova os volumes criados pelo Docker.

## Para rodar tudo junto (com limitação de CPU)

1. Subir o ambiente (PostgreSQL) com o Docker :

Na raiz do projeto, executar:

``  docker compose up --build``

2. Rode os testes do Galting

``  
cd stress-test
./run-tests.sh
``

3. (Opcional) Recomendo monitorar seus containers através do comando:

``docker stats``

## Referências

1. https://github.com/willy-r/rinha-de-backend-2023-javinha
2. https://github.com/boaglio/rinha-backend-2023-q3-java
3. https://github.com/brunoborges/rinha-app
4. https://github.com/juniorleaoo/rinha-de-backend-2023-q3-java/

## Agradecimentos

Aos [@BrunoBorges](https://github.com/brunoborges) pela mentoria, ao [@Boaglio](https://github.com/boaglio) pela base pra rodar reativo.

E claro, ao grande [@zanfranceschi](https://github.com/zanfranceschi) por organizar esse desafio.