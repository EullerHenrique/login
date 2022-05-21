//keycloak-connect: Este módulo simplifica a implementação de um aplicativo Node.js que usa o Keycloak para suas necessidades de autenticação e autorização.
import KeycloakConnect from "keycloak-connect";

//express-session: Uma estrutura do lado do servidor HTTP usada para criar e gerenciar um middleware de sessão.
import session from 'express-session';

//KeycloakConnect.prototype.acessDenied: Retorna uma resposta HTTP 403 (Forbidden) para o cliente.
KeycloakConnect.prototype.accessDenied = (req, res) => {
    res.send('Não autorizado');
}

//memoryStore: Implementação de um armazenamento de sessão em memória.
export const memoryStore = new session.MemoryStore();

const config: KeycloakConnect.KeycloakConfig = {
    
    //Auth-server = Servidor de autenticação
    "auth-server-url": "http://localhost:8081/auth",

    //Realm

    //Um realm gerencia um conjunto de usuários, credenciais, funções e grupos. Um usuário que pertence a ele,
    //e ele efetua login nesse realm. Realms são isolados uns dos outros e só podem gerenciar e autenticar os
    //usuários que pertencem a ele.
    realm: "login",
    
    //Client

    //Clients são entidades que podem solicitar o Keycloak para autenticar um usuário. Na maioria das vezes,
    //os clients são aplicativos e serviços que desejam usar o Keycloak para se proteger e fornecer uma solução
    //de logon único. Os clients também podem ser entidades que desejam apenas solicitar informações de identidade
    //ou um token de acesso, para que possam invocar com segurança outros serviços na rede protegidos pelo Keycloak.

    resource: 'node',
    
    //confidential-port: Porta de comunicação segura.
    "confidential-port": 0,

    //ssl-required: Indica se o acesso ao serviço deve ser feito através de um protocolo seguro.
    //external: true para todas as solicitações externas
    //none: false
    "ssl-required": "external"
};

const keycloak = new KeycloakConnect({store: memoryStore}, config);

export default keycloak;