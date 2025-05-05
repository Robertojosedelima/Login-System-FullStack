Login-System-fullStack
Esta API foi desenvolvida utilizando Spring Security e Keycloak para implementar um sistema de autenticação e autorização seguro. Ela oferece endpoints para login, logout e refresh de tokens, construída com Java 23.

Funcionalidades
Login: Permite que usuários autenticados obtenham um token de acesso e um token de refresh.
Logout: Invalida o token de acesso atual do usuário.
Refresh Token: Permite que usuários com um token de refresh válido obtenham um novo token de acesso sem precisar fornecer suas credenciais novamente.
Proteção Completa: A API utiliza certificados digitais para garantir a confidencialidade e integridade das comunicações através do protocolo HTTPS, complementando a segurança oferecida pelo Spring Security e Keycloak.
Tecnologias Utilizadas
Java 23
Spring Boot
Spring Security
Keycloak
Pré-requisitos
Java Development Kit (JDK) 23 instalado.
Maven instalado.
Uma instância do Keycloak em execução e configurada com:
Um Realm.
Um Client configurado para sua aplicação.
Usuários com credenciais definidas.
As configurações do Keycloak (URL do servidor, realm e client ID) devem estar configuradas no arquivo de propriedades da aplicação Spring Boot (application.properties ou application.yml).
Um certificado digital válido instalado e configurado em um keystore para habilitar o protocolo HTTPS.
Configuração
Configurar o Keycloak:

Crie um Realm no seu servidor Keycloak (se ainda não existir).
Crie um Client dentro do Realm. Defina o "Access Type" apropriado para sua aplicação.
Configure as "Valid Redirect URIs" e "Web Origins" do seu client, se necessário.
Crie usuários dentro do Realm e defina suas credenciais.
Obtenha o "Client ID" do seu client.
Configurar a Aplicação Spring Boot com Certificado Digital:

Abra o arquivo src/main/resources/application.properties ou src/main/resources/application.yml.

Configure as propriedades do Keycloak de acordo com a sua instância, substituindo os valores de exemplo pelas suas configurações reais:

Properties

spring.application.name=login-system-fullstack
spring.profiles.active=dev

spring.security.oauth2.resourceserver.jwt.issuer-uri=http://<seu-servidor-keycloak>:<porta>/realms/<seu-realm>

spring.security.oauth2.client.registration.keycloak.client-id=<seu-client-id>
spring.security.oauth2.client.registration.keycloak.client-secret=<seu-client-secret> # Mantenha este valor seguro
spring.security.oauth2.client.registration.keycloak.scope=openid, profile, email

spring.security.oauth2.client.provider.keycloak.issuer-uri=http://<seu-servidor-keycloak>:<porta>/realms/<seu-realm>

server.port=8080
server.ssl.key-store=file:<caminho-para-seu-keystore> # Caminho para o arquivo do seu keystore com o certificado
server.ssl.key-store-password=<senha-do-seu-keystore> # Senha para acessar o keystore (mantenha seguro)
server.ssl.key-store-type=<tipo-do-seu-keystore> # Ex: JKS, PKCS12
server.ssl.key-alias=<alias-do-seu-certificado> # Alias do seu certificado dentro do keystore
server.ssl.key-password=<senha-da-sua-chave-privada> # Senha da chave privada do certificado (mantenha seguro)
ou em YAML:

YAML

spring:
application:
name: login-system-fullstack
profiles:
active: dev
security:
oauth2:
resourceserver:
jwt:
issuer-uri: http://<seu-servidor-keycloak>:<porta>/realms/<seu-realm>
client:
registration:
keycloak:
client-id: <seu-client-id>
client-secret: <seu-client-secret> # Mantenha este valor seguro
scope: openid, profile, email
provider:
keycloak:
issuer-uri: http://<seu-servidor-keycloak>:<porta>/realms/<seu-realm>
server:
port: 8080
ssl:
key-store: file:<caminho-para-seu-keystore> # Caminho para o arquivo do seu keystore com o certificado
key-store-password: <senha-do-seu-keystore> # Senha para acessar o keystore (mantenha seguro)
key-store-type: <tipo-do-seu-keystore> # Ex: JKS, PKCS12
key-alias: <alias-do-seu-certificado> # Alias do seu certificado dentro do keystore
key-password: <senha-da-sua-chave-privada> # Senha da chave privada do certificado (mantenha seguro)
Endpoints da API
POST /login:

Corpo da Requisição (Form Data):
username: Nome de usuário.
password: Senha do usuário.
Resposta (JSON):
JSON

{
"access_token": "...",
"expires_in": 300,
"refresh_token": "...",
"refresh_expires_in": 1800,
"token_type": "Bearer",
"not-before-policy": 0,
"scope": "openid profile email"
}
POST /logout:

Cabeçalho da Requisição:
Authorization: Bearer <access_token>
Resposta:
204 No Content em caso de sucesso.
POST /refresh-token:

Corpo da Requisição (Form Data):
grant_type: refresh_token
refresh_token: <refresh_token_obtido_no_login>
Resposta (JSON):
JSON

{
"access_token": "...",
"expires_in": 300,
"refresh_token": "...",
"refresh_expires_in": 1800,
"token_type": "Bearer",
"not-before-policy": 0,
"scope": "openid profile email"
}
Como Executar a Aplicação
Clone este repositório (se aplicável).
Navegue até o diretório raiz do projeto.
Execute o comando Maven para construir a aplicação:
Bash

mvn clean install
Execute a aplicação Spring Boot:
Bash

mvn spring-boot:run
Ou execute o arquivo JAR gerado em target/:
Bash

java -jar target/Login-System-fullStack-*.jar
Certifique-se de que o Keycloak esteja rodando e acessível antes de iniciar a aplicação. A API estará acessível através do protocolo HTTPS na porta 8080, utilizando o certificado digital configurado.
Segurança
Esta API utiliza Spring Security para proteger os endpoints e integra com o Keycloak para autenticação e autorização baseada em tokens JWT.
A configuração do spring.security.oauth2.resourceserver.jwt.issuer-uri garante que a API valide os tokens emitidos pelo seu Keycloak.
O endpoint de logout requer um token de acesso válido para invalidar a sessão do usuário.
O endpoint de refresh token permite obter novos tokens de acesso utilizando um token de refresh, sem a necessidade de autenticação completa novamente.
A comunicação com a API é protegida por HTTPS, utilizando um certificado digital. Isso garante a criptografia dos dados transmitidos entre o cliente e o servidor, protegendo contra interceptação e manipulação. As informações sensíveis do certificado (keystore e senhas) devem ser mantidas em segurança e não compartilhadas publicamente.
Próximos Passos (Opcional)
Implementar testes unitários e de integração.
Adicionar tratamento de erros mais detalhado.
Configurar roles e permissões no Keycloak e aplicá-las na API para controle de acesso mais granular.
Documentar a API utilizando ferramentas como Swagger/OpenAPI.
Implementar mecanismos de segurança adicionais, como rate limiting.
Sinta-se à vontade para contribuir com este projeto ou utilizá-lo como base para seus próprios sistemas de autenticação seguros!