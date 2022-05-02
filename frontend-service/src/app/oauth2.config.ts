import {AuthConfig} from 'angular-oauth2-oidc';

export const authCodeFlowConfig: AuthConfig = {
  loginUrl: 'http://127.0.0.1:9000/oauth2/authorize',
  tokenEndpoint: 'http://127.0.0.1:9000/oauth2/token',
  redirectUri: window.location.origin,
  clientId: 'finance-tracker-frontend',
  responseType: 'code',
  requestAccessToken: true,
  oidc: false,
  scope: '',
  requireHttps: false,
  clearHashAfterLogin: false,
  showDebugInformation: true
};
