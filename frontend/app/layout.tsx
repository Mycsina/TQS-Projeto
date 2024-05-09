import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Navbar from "@/components/navbar";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
    title: "QuickServe",
    description: "Restaurant management system",
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
            <body className={inter.className + "h-screen flex flex-col overflow-x-hidden"}>
                <Navbar />
                <main className="m-10 flex-grow">
                    {children}
                </main>
            </body>
        </html>
    );
}
