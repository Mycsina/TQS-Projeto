'use client';

import React from 'react';
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import Link from "next/link";
import { signUp } from "@/server/SignUp";

export default function Signup() {
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const firstName = (e.currentTarget.elements.namedItem("first-name") as HTMLInputElement).value;
    const lastName = (e.currentTarget.elements.namedItem("last-name") as HTMLInputElement).value;
    const email = (e.currentTarget.elements.namedItem("email") as HTMLInputElement).value;
    const password = (e.currentTarget.elements.namedItem("password") as HTMLInputElement).value;
    const phone = (e.currentTarget.elements.namedItem("phone") as HTMLInputElement).value;

    const name = `${firstName} ${lastName}`;
    const result = await signUp(name, email, password, phone);
    if (result) {
      console.log(result);
      localStorage.setItem("loggedIn", email);
      window.location.href = "/dashboard";
    } else {
      console.log(result)
    }
  };

  return (
    <main className="flex w-full items-center justify-center p-4">
      <div className="w-full max-w-md space-y-6">
        <div className="space-y-2 text-center">
          <h1 className="text-3xl font-bold text-gray-50">Sign Up</h1>
          <p className="text-gray-400">Enter your information to create an account</p>
          <div className="mt-4 text-center text-sm">
            Already have an account?
            <span> </span>
            <Link className="underline text-gray-50" href="/login">
              Login
            </Link>
          </div>
        </div>
        <form className="space-y-4" onSubmit={handleSubmit}>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label className="text-gray-400" htmlFor="first-name">
                First name
              </Label>
              <Input className="bg-gray-800 text-gray-50" id="first-name" placeholder="Lee" required />
            </div>
            <div className="space-y-2">
              <Label className="text-gray-400" htmlFor="last-name">
                Last name
              </Label>
              <Input className="bg-gray-800 text-gray-50" id="last-name" placeholder="Robinson" required />
            </div>
          </div>
          <div className="space-y-2">
            <Label className="text-gray-400" htmlFor="phone">
              Phone
            </Label>
            <Input className="bg-gray-800 text-gray-50" id="phone" placeholder="1234567890" required />
          </div>
          <div className="space-y-2">
            <Label className="text-gray-400" htmlFor="email">
              Email
            </Label>
            <Input className="bg-gray-800 text-gray-50" id="email" placeholder="m@example.com" required type="email" />
          </div>
          <div className="space-y-2">
            <Label className="text-gray-400" htmlFor="password">
              Password
            </Label>
            <Input className="bg-gray-800 text-gray-50" id="password" required type="password" />
          </div>
          <Button className="w-full" type="submit">
            Sign Up
          </Button>
        </form>
      </div>
    </main>
  );
}
