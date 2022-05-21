//express: É um framework para Node.js que fornece recursos mínimos para construção de servidores web (HTTP).
import express from 'express';

//express-session: Uma estrutura do lado do servidor HTTP usada para criar e gerenciar um middleware de sessão.
import session from 'express-session';

//request: A solicitação foi projetada para ser a maneira mais simples possível de fazer chamadas http
import request from "request";

import keycloak, {memoryStore} from './keycloak';

var app = express();

//O EJS é uma engine de visualização, com ele conseguimos de uma maneira fácil e simples transportar dados do back-end para o front-end, 
//basicamente conseguimos utilizar códigos em javascript no html de nossas páginas.
app.set("view engine", 'ejs');


app.use(
  //session: 
  session({
    //secret: Chave secreta para criptografar e descriptografar a sessão.
    secret: '123456789',
    //resave: Força que uma sessão inicializada seja salva mesmo quando não modificada
    resave: false,
    //saveUninitialized: Força uma sessão "não inicializada" a ser salva no armazenamento. 
    //Uma sessão é "não inicializada" quando é nova, mas não modificada.
    saveUninitialized: true,
    //store: Armazena a sessão em um armazenamento de sessão.
    store: memoryStore,
    //cookie: Define as configurações do cookie.
    cookie: {
      maxAge: 1000 * 60 * 10 // 10 minutes
    }
  })
)

//keycloak.middleware: Retorna um middleware que intercepta todas as solicitações HTTP e verifica se o usuário está autenticado.
app.use(keycloak.middleware({
  //logout: URL para onde o usuário será redirecionado após o logout.
  logout: '/logout',
  //admin: URL para onde o usuário será redirecionado após o login como administrador.
  admin: '/'
}));

//protect: O método protect() é usado para proteger uma rota.

app.get('/login', keycloak.protect(), (req, res)=>{
 
  //res.redirect("/user")
  //ou
  //res.redirect("/admin")

  //@ts-ignore  
  res.redirect("/"+req.kauth.grant.access_token.content.realm_access.roles[0])

});

//protect: O método protect() é usado para proteger uma rota.
//realm role: As aplicações geralmente atribuem acesso e permissões a funções específicas, em vez de usuários 
//individuais,pois lidar com usuários pode ser muito difícil de gerenciar.
//realm: user -> O usuário associado à role user pode acessar o endpoint /user
app.get('/user', keycloak.protect("realm:user"), (req, res)=>{

  request.get("http://localhost:8080/user",{
    
    headers: {
      
      //@ts-ignore
      "Authorization": "Bearer " + req.kauth.grant.access_token.token
    
    }

  },
  (error, response, body) => {
  
    res.render("../views/user.ejs", {message: body})
  
  });

});

//protect: O método protect() é usado para proteger uma rota.
//realm role: As aplicações geralmente atribuem acesso e permissões a funções específicas, em vez de usuários 
//individuais,pois lidar com usuários pode ser muito difícil de gerenciar.
//realm: admin -> O usuário associado à role admin pode acessar o endpoint /admin
app.get('/admin', keycloak.protect("realm:admin"), (req, res)=>{
  

  request.get("http://localhost:8080/admin",{
    
    headers: {
    
      //@ts-ignore
      "Authorization": "Bearer " + req.kauth.grant.access_token.token
      
    }

  },
  (error, response, body) => {
   
    res.render("../views/admin.ejs", {message: body})
  
  });

});

app.listen(8082, ()=>{});

export default app;
