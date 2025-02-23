Documento Funcional: Funcionalidades Adicionais para API de Login
1. Verificação de Conta (Email/Telefone)
   Descrição:
   Quando um usuário cria uma conta ou tenta fazer login, a API deve verificar se o usuário tem sua conta verificada (seja por e-mail ou telefone). Caso contrário, a API deve permitir um processo de verificação.

Fluxo:
Verificação de Conta:

Quando o usuário tentar fazer login, a API verifica se o e-mail ou telefone está marcado como "verificado" no banco de dados.
Caso não esteja verificado, a API retorna um erro indicando que a conta precisa ser verificada.
Envio de Código de Verificação:

Se o e-mail ou telefone não estiver verificado, a API envia um código de verificação para o e-mail ou telefone do usuário.
O usuário deve informar esse código para concluir o processo de verificação.
Validação do Código de Verificação:

Quando o código é enviado, o usuário deve inseri-lo no front-end.
A API valida o código informado. Se correto, a conta é marcada como verificada, e o login prossegue normalmente.
Endpoint:
POST /verify-email: Envia um código de verificação para o e-mail ou telefone.
POST /validate-verification-code: Valida o código de verificação.
2. Bloqueio de Conta por Tentativas Falhas
   Descrição:
   A API deve bloquear temporariamente o login de um usuário após múltiplas tentativas falhas para prevenir ataques de força bruta.

Fluxo:
Tentativas de Login:
Cada tentativa de login é registrada com um contador de falhas no banco de dados (pode ser armazenado em um campo na tabela de usuários).
Limite de Tentativas:
O sistema deve permitir um número limitado de tentativas de login (ex: 5 tentativas). Após isso, a conta do usuário será temporariamente bloqueada (ex: por 15 minutos).
Mensagem de Erro:
Se a conta for bloqueada, a API retorna uma mensagem informando que o usuário deve esperar para tentar novamente ou seguir o fluxo de recuperação de senha.
Endpoint:
POST /login: Tenta o login e registra falhas. Retorna erro caso o limite de tentativas seja atingido.
3. Expiração de Tokens e Renovação (Refresh Token)
   Descrição:
   Tokens de acesso têm um tempo de vida limitado, e quando expiram, o usuário precisa usar um refresh token para obter um novo token de acesso, sem precisar realizar o login novamente.

Fluxo:
Expiração do Token de Acesso:
O token de acesso tem um tempo de expiração curto (ex: 15 minutos).
Uso do Refresh Token:
Quando o token de acesso expira, o cliente pode usar o refresh token para solicitar um novo token de acesso.
Regeneração de Tokens:
A API valida o refresh token e, se for válido, gera um novo token de acesso e, opcionalmente, um novo refresh token.
Endpoint:
POST /refresh-token: Recebe o refresh token e retorna um novo token de acesso e refresh token.
4. Revogação de Tokens
   Descrição:
   Se o usuário fizer logout ou alterar a senha, todos os tokens emitidos anteriormente devem ser revogados para garantir que tokens antigos não possam ser usados indevidamente.

Fluxo:
Logout:

Quando o usuário faz logout, todos os tokens de acesso e refresh tokens ativos para aquele usuário devem ser invalidados.
Alteração de Senha:

Se o usuário alterar a senha, todos os tokens de acesso e refresh tokens anteriores devem ser revogados automaticamente.
Endpoint:
POST /logout: Revoga todos os tokens de acesso e refresh tokens do usuário.
POST /change-password: Altera a senha e revoga tokens.
5. Autenticação Multifatorial (2FA)
   Descrição:
   A autenticação multifatorial (2FA) adiciona uma camada extra de segurança ao processo de login, exigindo um segundo fator, como um código enviado por SMS ou e-mail.

Fluxo:
Habilitação de 2FA:

O usuário pode habilitar 2FA em sua conta.
A API gera uma chave secreta (como um código QR para aplicativos de autenticação ou um código para SMS).
Login com 2FA:

Durante o login, após a validação da senha, a API solicita que o usuário insira o código de autenticação (via app ou SMS).
Validação do Código:

A API valida o código fornecido e, se correto, o login é completado.
Endpoint:
POST /enable-2fa: Habilita a autenticação multifatorial para o usuário.
POST /login-2fa: Realiza a validação da senha e do código de 2FA.
6. Claims Personalizados no Token
   Descrição:
   O JWT pode incluir informações adicionais sobre o usuário (como papéis ou permissões) para facilitar o controle de acesso em APIs.

Fluxo:
Inclusão de Claims:

Durante a emissão do token JWT, a API inclui claims personalizados, como o papel do usuário (ex: role: admin) ou permissões específicas (ex: permissions: ['read', 'write']).
Acesso Baseado em Claims:

Quando o usuário faz uma requisição em uma API, a API pode verificar os claims do token para determinar o nível de acesso do usuário e permitir ou negar a ação.
Endpoint:
A adição de claims pode ser feita durante o processo de login ou no momento da criação do JWT.
7. Gerenciamento de Sessões
   Descrição:
   Permite ao usuário ver e gerenciar suas sessões ativas, possibilitando encerrar sessões em dispositivos não reconhecidos ou em casos de segurança.

Fluxo:
Visualização de Sessões:
A API permite que o usuário veja todas as suas sessões ativas, incluindo informações sobre o dispositivo e localização.
Encerramento de Sessões:
O usuário pode encerrar uma sessão específica ou todas as suas sessões ativas, caso desconfie de atividades suspeitas.
Endpoint:
GET /sessions: Retorna todas as sessões ativas do usuário.
POST /sessions/{sessionId}/logout: Encerra uma sessão específica.
8. Verificação de Requisições de Login em Dispositivos Diferentes
   Descrição:
   Quando um login é realizado de um novo dispositivo ou local, o sistema pode solicitar uma verificação adicional (como um código enviado por e-mail ou SMS).

Fluxo:
Detecção de Novo Dispositivo ou Local:
A API detecta se o login está sendo realizado de um novo dispositivo ou localização (isso pode ser feito analisando o IP, o agente do usuário ou o dispositivo).
Envio de Código de Verificação:
Se o login for detectado como novo, a API envia um código de verificação para o e-mail ou telefone do usuário.
Validação do Código:
O usuário insere o código enviado para confirmar que o login é legítimo.
Endpoint:
POST /verify-new-login: Envia um código de verificação quando o login é feito a partir de um dispositivo ou local novo.
Essas funcionalidades ajudam a fortalecer a segurança da sua API de login e oferecem uma melhor experiência para os usuários, tornando o processo mais seguro e flexível.