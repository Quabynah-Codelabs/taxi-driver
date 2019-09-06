const functions = require("firebase-functions");
const admin = require("firebase-admin");
const app = require("./api");
admin.initializeApp();

// Export backend to server
exports.api = functions.https.onRequest(app);
