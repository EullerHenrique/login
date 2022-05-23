# Login

## Sumário 

- [Conceito](#conceito)

- [Tecnologias Utilizadas](#tecnologias-utilizadas)

- [Configuração](#configuração)

- [Endpoints](#endpoints)

- [Diagrama](#diagrama)

## Conceito

"

Keycloak é uma ferramenta de gerenciamento de acesso e identidade IAM (Identity and Access Management) de código aberto com foco em ferramentas modernas tais como single-page applications (SPA), aplicativos móveis e APIs REST.

Keycloak fornece páginas de login totalmente personalizáveis, incluindo autenticação forte, também com vários fluxos, como recuperação de senhas, exigindo que os usuários atualizem regularmente suas senhas,
aceitação de termos/ condições e muito mais.

Todas as páginas visíveis para seus usuários suportam temas personalizados, tornando muito fácil modificar a aparência das páginas para integrar-se à sua marca corporativa e aos aplicativos existentes.
Ao delegar autenticação ao Keycloak, seus aplicativos não precisam se preocupar com diferentes mecanismos de autenticação ou como armazenar senhas com segurança. Esta abordagem também fornece um nível
mais alto de segurança, pois os aplicativos não têm acesso direto ao usuário e credenciais, eles recebem, em vez disso, tokens de segurança que lhes dão apenas acesso para o que eles precisam.

O Keycloak fornece login único, bem como recursos de gerenciamento de sessão, permitindo que os usuários acessem vários aplicativos, tendo apenas que se autenticar uma vez. Todos os usuários e administradores
têm total visibilidade de onde os usuários são autenticados e pode encerrar sessões remotamente quando necessário.

Keycloak baseia-se em protocolos padrões da indústria com suporte para OAuth 2.0, OpenID Connect, e SAML 2.0. O uso de protocolos padrões da indústria é importante tanto do ponto de vista de segurança e perspectiva 
em termos de tornar mais fácil a integração com aplicativos já existentes com aplicativos novos ou aplicativos de terceiros.

Realm

Um realm gerencia um conjunto de usuários, credenciais, funções e grupos. Um usuário que pertence a ele,
e ele efetua login nesse realm. Realms são isolados uns dos outros e só podem gerenciar e autenticar os
usuários que pertencem a ele.

Client

Clients são entidades que podem solicitar o Keycloak para autenticar um usuário. Na maioria das vezes,
os clients são aplicativos e serviços que desejam usar o Keycloak para se proteger e fornecer uma solução
de logon único. Os clients também podem ser entidades que desejam apenas solicitar informações de identidade
ou um token de acesso, para que possam invocar com segurança outros serviços na rede protegidos pelo Keycloak.

Realm Role

As aplicações geralmente atribuem acesso e permissões a funções específicas, em vez de usuários individuais,
pois lidar com usuários pode ser muito difícil de gerenciar. Vamos criar app-user e app-admin roles realm, 
atribuindo as roles correspondente ao gateway-microservice (user, admin).

User

Usuários são entidades capazes de efetuar login no seu sistema. Eles podem ter atributos associados
a eles mesmos, como e-mail, nome de usuário, endereço, número de telefone e dia do nascimento.
Eles podem ser associados ao grupo e ter roles específicas atribuídas a eles.

" - Fonte: https://dev.delivery/seguranca-de-aplicacoes-com-keycloak/


"

Em vez de fazer login em aplicativos individuais, os usuários se autenticam no Keycloak. Isso significa que os aplicativos individuais não precisam implementar seus próprios formulários de login, autenticação e armazenamento de usuários e sessões. Depois de fazer login no Keycloak, os usuários não precisam fazer login novamente para acessar um aplicativo diferente. Da mesma forma, uma vez desconectado do Keycloak, os usuários não precisam sair dos aplicativos individuais. Habilitar o login com redes sociais também é fácil. A configuração para esses itens pode ser adicionada no console administrativo do Keycloak. Nenhum código ou alteração é necessário para as aplicações.

" - Fonte: http://www.tecnisys.com.br/noticias/2020/conhecendo-o-keycloak#:~:text=Isso%20significa%20que%20os%20aplicativos,para%20acessar%20um%20aplicativo%20diferente.

"

O Keycloak possui uma série de funcionalidades que são de interesse de desenvolvedores que querem colocar uma camada de autenticação unificada em suas aplicações, são elas:
- Single-Sign On e Single-Sign Out
- Suporte à OpenID Connect
- Suporte à OAuth 2.0
- Suporte à SAML
- Identity Brokering – Autentique com OpenID Connect externo ou provedores de identidade SAML
- Login social – Habilite o login com Google, GitHub, Facebook, Twitter e outras redes sociais
- Federação do usuário – Sincronize usuários de servidores LDAP e Active Directory
- Kerberos bridge – Autentique automaticamente os usuários que estão logados em um servidor Kerberos
- Console Admin para gerenciamento central de usuários, funções, mapeamentos de funções, clientes e configuração
- Console de gerenciamento de contas que permite aos usuários gerenciar centralmente suas contas
- Suporte a temas – personalize todas as páginas voltadas para o usuário para integrá-las aos seus aplicativos e marcas
- Autenticação de dois fatores – suporte para TOTP / HOTP via Google Authenticator ou FreeOTP
- Fluxos de login – auto-registro opcional do usuário, recuperar senha, verificar e-mail, exigir atualização de senha etc.
- Gerenciamento de sessão – os próprios administradores e usuários podem visualizar e gerenciar as sessões do usuário
- Mapeadores de tokens – Mapeie atributos de usuário, funções e demais recursos como desejar em tokens e instruções
- Suporte a CORS – os adaptadores de cliente possuem suporte integrado para CORS
- Service Provider Interfaces (SPI) – uma série de SPIs para permitir a personalização de vários aspectos do servidor. Fluxos de autenticação, provedores de federação - de usuário, mapeadores de protocolo e muito mais
- Adaptadores de cliente para aplicativos JavaScript, WildFly, JBoss EAP, Fuse, Tomcat, Jetty, Spring etc.
- Suporta qualquer plataforma/linguagem que tenha uma biblioteca OpenID Connect Relying Party ou biblioteca de provedor de serviços SAML 2.0


" - Fonte: https://blog.4linux.com.br/gerenciando-identidades-e-acessos-com-keycloak-parte-1/

"

A intenção da ferramenta é facilitar a proteção de aplicativos e serviços com pouca ou nenhuma criptografia.
Um IdP permite que um aplicativo (geralmente chamado de Provedor de Serviços ou SP) delegue sua autenticação.

Isso tem, entre outras coisas, várias vantagens:

- Ele permite que os desenvolvedores se concentrem na funcionalidade de negócios, não tendo que se preocupar com os aspectos de segurança da autenticação,
seja integrando diretamente uma biblioteca que suporta um dos dois protocolos ou usando um módulo no servidor web ou um adaptador Keycloak 
- Ser capaz de centralizar a autenticação e, portanto, habilitar a autenticação de logon único (SSO)
- Ser capaz de unificar os métodos de autenticação e fazê-los evoluir sem modificar as aplicações.
- Reinventar a autenticação de aplicativos SaaS e, assim, controlar a proliferação de identidades digitais; A desativação de contas é simplificada (excluir uma conta SaaS quando um funcionário sai) não é mais esquecido.

" - Fonte: https://blog.desdelinux.net/pt/keycloak-una-solucion-de-gestion-de-acceso-e-identidad-de-codigo-abierto/

## Tecnologias Utilizadas

### Front-End
 
 - Typescript
 - Node.JS
 - Express
 - EJS
 - KeyCloak

### Back-End

 - Java
 - Spring Boot
 - Docker
 - Lombok
 - KeyCloak
 - MailHog

## Configuração

### Docker
  
  1. Clone o repósitorio
  2. Instale o docker (https://www.docker.com/products/docker-desktop/)
  3. Abra o docker
  4. Abra o terminal
  5. Navegue até a pasta docker
  6. Digite docker-compose up -d

### Keycloak

 1. Acesse localhost:8081
 2. Acesse "Adminstration Console"
 3. Realize o login (User: admin, Senha: 12345)
 4. Aperte o botão "keycloak" localizado no canto superior direito
 5. Aperte o botão "Manage account"
 6. Aperte o botão "Personal Info"
 7. Atribua o valor "keycloak@gmail" ao atrbuto "Email"
 9. Aperte o botão "Back to security admin console" no canto superior direito
 10. Crie o Realm login
 11. Vá na aba login 
 12. Ative a opção Forgot password
 13. Vá na aba email 
 14. Atribua o valor "mailhog" ao atrbuto "Host"
 15. Atribua o valor "1025" ao atrbuto "Port"
 16. Atribua o valor "keycloak@gmail.com" ao atrbuto "From"
 17. Aperte o botão "Test Connection"
 18. Crie o Client node
 19. Atribua o valor "http://localhost:8082/*" ao atrbuto "Valid Redirect URIs"
 20. Crie o Client Spring
 21. Atribua o valor "http://localhost:8080/* ao atributo "Valid Redirect URIs"
 22. Crie a Role User
 23. Crie a Role Admin
 24. Crie o User admin (Atribua o valor admin@gmail.com ao atributo email)
 25. Crie o User euller (Atribua o valor euller@gmail.com ao atributo email)
 26. Atribua o User admin à Role Admin
 27. Atribua o User euller à Role euller
 
 Obs: Realize essa configuração somente se desejar uma confifuração personalizada, pois essa configuração já foi feita e foi salva em um volume do docker (pasta postgres) 

### Front-End
 
  1. Abra a pasta login_fron-end em uma IDE (Ex: Visual Studio Code) 
  2. Instale o node.js (https://nodejs.org/en/download/)
  3. Abra o terminal
  4. Digite npm install
  5. Digite npm start

### Back-End
  
  1. Abra a pasta login_back-end em uma IDE (Ex: IntelliJ IDEA) 
  2. Navegue pela IDE até LoginApplication 
  3. Aperte o botão play localizado ao lado de "public class LoginApplication"
 
## Endpoints

### KeyCloak

#### localhost:8081

<img width="603" alt="Captura de tela 2022-05-19 164111" src="https://user-images.githubusercontent.com/48317736/169389430-e241890f-3b82-4255-a066-a556c386b971.png">

### MailHog

#### localhost:8025

<img width="962" alt="Captura de tela 2022-05-23 133722" src="https://user-images.githubusercontent.com/48317736/169869537-96d3d807-00a4-41ec-bdde-c158ad6cd988.png">


### Front-End

#### localhost:8082/login

  1. Usuário: euller Senha: 12345     
  2. Usuário: admin Senha: 12345

<img width="632" alt="Captura de tela 2022-05-23 140023" src="https://user-images.githubusercontent.com/48317736/169870911-d2a68c16-a498-48c8-a660-fd43ed92a820.png">


#### localhost:8082/login - Esqueceu sua senha? 
 
<img width="704" alt="Captura de tela 2022-05-23 135409" src="https://user-images.githubusercontent.com/48317736/169870281-8954ee76-a152-440d-b1da-0c77df189bfd.png">
<img width="709" alt="Captura de tela 2022-05-23 135424" src="https://user-images.githubusercontent.com/48317736/169870282-5f6de893-c629-40f4-962f-e43ef801fede.png">
<img width="636" alt="Captura de tela 2022-05-23 135513" src="https://user-images.githubusercontent.com/48317736/169870276-a1cedac5-586e-40c9-a4d7-ed5b9ada72a9.png">
<img width="632" alt="Captura de tela 2022-05-23 135530" src="https://user-images.githubusercontent.com/48317736/169870289-76650c1e-67b1-46eb-9cd0-6528c1be7505.png">

#### localhost:8082/user

<img width="395" alt="Captura de tela 2022-05-19 162835" src="https://user-images.githubusercontent.com/48317736/169388330-a68750f9-24a3-46db-8f32-bbde53becfd8.png">

#### localhost:8082/admin

<img width="389" alt="Captura de tela 2022-05-19 162800" src="https://user-images.githubusercontent.com/48317736/169388728-10136070-ffc8-4f1d-b502-6ec16e1d4651.png">

### Back-End

#### localhost:8080/user

<img width="373" alt="Captura de tela 2022-05-19 164846" src="https://user-images.githubusercontent.com/48317736/169391369-a2a3394c-9230-48ca-9051-022bc86f9240.png">

#### localhost:8080/admin

<img width="377" alt="Captura de tela 2022-05-19 164933" src="https://user-images.githubusercontent.com/48317736/169391382-51e1738b-0878-4866-b336-38d09dba76ed.png">

## Diagrama

![KC drawio (2)](https://user-images.githubusercontent.com/48317736/168608333-84a2c74b-821b-4fc5-8072-0eaf7d958ddd.png)




