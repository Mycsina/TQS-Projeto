"use server";

import { API_URL, API_VERSION } from "@/lib/consts";

export async function login(email: string, password: string) {
  let body = {
    "email": email,
    "password": password
  }
  return fetch(`${API_URL}/api/${API_VERSION}/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  })
    .then(response => response.body)
    .then(data => data)
    .catch(error => console.error('Error:', error));
}