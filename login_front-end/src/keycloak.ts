import KeycloakConnect from "keycloak-connect";
import session from 'express-session';

KeycloakConnect.prototype.accessDenied = (req, res) => {
    res.json({message: 'Não autorizado'});
}

export const memoryStore = new session.MemoryStore();

const config: KeycloakConnect.KeycloakConfig = {
    realm: "login",
    "auth-server-url": "http://localhost:8081/auth",
    resource: 'node',
    "confidential-port": 0,
    "ssl-required": "external"
};

const keycloak = new KeycloakConnect({store: memoryStore}, config);

export default keycloak;