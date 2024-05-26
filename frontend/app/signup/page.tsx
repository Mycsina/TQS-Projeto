/**
 * v0 by Vercel.
 * @see https://v0.dev/t/9QT1uY111Jv
 * Documentation: https://v0.dev/docs#integrating-generated-code-into-your-nextjs-app
 */
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import Link from "next/link"

export default function Signup() {
  return (
    <main className="flex h-screen w-full items-center justify-center bg-gray-950 p-4">
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
        <div className="space-y-4">
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
        </div>
      </div>
    </main>
  )
}