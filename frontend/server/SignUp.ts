"use server";

import { API_URL, API_VERSION } from "@/lib/consts";

export async function signUp(name: string, email: string, password: string, phone: string) {
  let body = {
    "name": name,
    "email": email,
    "password": password,
    "phone": phone
  }
  return fetch(`${API_URL}/api/${API_VERSION}/auth/signup`, {
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