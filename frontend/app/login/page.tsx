'use client';

import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import Link from "next/link"
import { Button } from "@/components/ui/button"
import { login } from "@/server/Login"

export default function Component() {
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const email = (e.currentTarget.elements.namedItem("email") as HTMLInputElement).value
    const password = (e.currentTarget.elements.namedItem("password") as HTMLInputElement).value
    const data = await login(email, password)
    if (data) {
      console.log(data)
      localStorage.setItem("loggedIn", email)
      window.location.href = "/dashboard"
    } else {
      console.error("Failed to login")
    }
  }
  return (
    <main className="flex w-full items-center justify-center">
      <div className="w-full max-w-md space-y-6 rounded-lg bg-gray-900 p-6 shadow-lg dark:bg-gray-900">
        <div className="space-y-2 text-center">
          <h1 className="text-3xl font-bold text-gray-50">Welcome back</h1>
          <p className="text-gray-400 dark:text-gray-400">Enter your email and password to sign in.</p>
        </div>
        <form className="space-y-4" onSubmit={handleSubmit}>
          <div className="space-y-2">
            <Label className="text-gray-50" htmlFor="email">
              Email
            </Label>
            <Input
              className="bg-gray-800 text-gray-50 placeholder:text-gray-400"
              id="email"
              placeholder="m@example.com"
              required
              type="email"
            />
          </div>
          <div className="space-y-2">
            <div className="flex items-center justify-between">
              <Label className="text-gray-50" htmlFor="password">
                Password
              </Label>
              <Link
                className="text-sm font-medium underline underline-offset-2 text-gray-400 hover:text-gray-300 dark:text-gray-400 dark:hover:text-gray-300"
                href="#"
              >
                Forgot password?
              </Link>
            </div>
            <Input
              className="bg-gray-800 text-gray-50 placeholder:text-gray-400"
              id="password"
              required
              type="password"
            />
          </div>
          <Button className="w-full bg-gray-800 text-gray-50 hover:bg-gray-700" type="submit">
            Sign in
          </Button>
        </form>
        <div className="mt-4 text-center text-sm text-gray-400 dark:text-gray-400">
          Don't have an account?
          <span> </span>
          <Link
            className="font-medium underline underline-offset-2 text-gray-400 hover:text-gray-300 dark:text-gray-400 dark:hover:text-gray-300"
            href="/signup"
          >Sign up
          </Link>
        </div>
      </div>
    </main>
  )
}