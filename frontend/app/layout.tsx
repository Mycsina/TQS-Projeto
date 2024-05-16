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
        <html lang="en" className="dark">
            <body className={inter.className + "h-screen flex flex-col overflow-x-hidden bg-slate-50 dark:bg-slate-900 dark:text-slate-100"}>
                <Navbar />
                <main className="m-10 mt-20 flex-grow">
                    {children}
                </main>
            </body>
        </html>
    );
}
