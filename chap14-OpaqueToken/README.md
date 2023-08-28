# Chapter 14 - OAuth 2 Opaque Token

## Opaque token

When the client makes calls to resource server's API endpoints that require authorization using an opaque token,
resource server uses the introspection endpoint of the authorization server in order to validate the token and obtain
information about the client.

## Getting started

- Add an entry to your machine's /etc/hosts file: `127.0.0.1 auth-server`. This step is necessary to prevent session
  cookie from being overwritten between the authorization server and API client server when running both servers on your
  local PC.
