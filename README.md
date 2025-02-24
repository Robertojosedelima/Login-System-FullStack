API de Login - Keycloak
Esta é uma API de autenticação que utiliza o Keycloak como provedor de token de autenticação. Ela per

Endpoints
1. POST /log-in
   Este endpoint permite realizar o login de um usuário, gerando um token JWT.

R
Método: `PPOST
URL: `/log/log-in
Corpo da requisição (J
json
Copiar
Editar
{
"username": "usuário",
"password": "senha"
}
Resposta:
Código de Status:200 OK
Corpo da Resposta (JSON)
j
Copiar
Editar
{
"access_token": "token_de_acesso_jwt",
"refresh_token": "token_de_refresh"
}
2. POST /log-out
   Es

Requisição:
Método: POST
URL: `/log-out
Cabeçalhos:
Authorization: `BearBearer {access_token}
Resposta:
Código de Status: `200200 OK
Corpo da Resposta (JSO
json
Copiar
Editar
{
"message": "Usuário deslogado com sucesso"
}
3. POST /refresh
   Este

Requisiç
Método: POST
URL: `/re/refresh
Corpo da requisição (J
json
Copiar
Editar
{
  "refresh_token": "token_de_refresh"
}
R
Código de Status: `200 O200 OK
Corpo da Resposta (JSON):
json
Copiar
Editar
{
"access_token": "novo_token_de_acesso_jwt"
}
Configuração do Ke
A API está

Instalar e con
Configurar
Definir o
Segurança
Tokens de acesso: O token JWT é utAuthorization para chamada
Tokens de refresh: São utiliz
Exemplos de Uso
Login
bash
Copiar
Editar
curl -X POST http://api.exemplo.com/log-in \
-H "Content-Type: application/json" \
-d '{"username": "user", "password": "password"}'
Logou
bash
Copiar
Editar
curl -X POST http://api.exemplo.com/log-out \
-H "Authorization: Bearer {access_token}"
Refresh
bash
Copiar
Editar
curl -X POST http://api.exemplo.com/refresh \
-H "Content-Type: application/json" \
-d '{"refresh_token": "{refresh_token}"}'
De
Keycl
Frameworks pa
Contribuiç
Contribuiçõ

Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detal