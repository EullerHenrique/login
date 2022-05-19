import express from 'express';
import keycloak, {memoryStore} from './keycloak';
import session from 'express-session';
import request from "request";

var app = express();
app.set("view engine", 'ejs');

app.use(
  session({
    secret: '123456789',
    resave: false,
    saveUninitialized: true,
    store: memoryStore,
    cookie: {
      maxAge: 1000 * 60 * 10 // 10 minutes
    }
  })
)

app.use(keycloak.middleware({
  logout: '/logout',
  admin: '/'
}));

app.get('/login', keycloak.protect(), (req, res)=>{
 
  //@ts-ignore  
  res.redirect("/"+req.kauth.grant.access_token.content.realm_access.roles[0])

});


app.get('/user', keycloak.protect("realm:user"), (req, res)=>{

  request.get("http://localhost:8080/user",{
    headers: {
      //@ts-ignore
      "Authorization": "Bearer " + req.kauth.grant.access_token.token.split(" ").slice(-1)[0]
    }
  },
  (error, response, body) => {
    res.render("../views/user.ejs", {message: body})
  });

});


app.get('/admin', keycloak.protect("realm:admin"), (req, res)=>{
  request.get("http://localhost:8080/admin",{
    headers: {
      //@ts-ignore
      "Authorization": "Bearer " + req.kauth.grant.access_token.token.split(" ").slice(-1)[0]
    }
  },
  (error, response, body) => {
    res.render("../views/admin.ejs", {message: body})
  });

});

app.listen(8082, ()=>{});

export default app;
