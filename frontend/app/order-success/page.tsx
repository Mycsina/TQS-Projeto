import Link from "next/link";
import { Button } from "@/components/ui/button";

export default function OrderSucess() {
    return (
        <div className="flex flex-col bg-gray-950 w-96 h-96 mx-auto rounded-xl shadow-xl p-10 text-center">
            <h1 className="text-4xl font-bold">Order Sucess</h1>
            <h1 className="text-2xl font-bold mt-4">Order Number</h1>
            <h1 className="text-2xl font-bold">#12345</h1>

            <div className="flex-grow"></div>
            <Link href="/">
                <Button className="font-bold w-full">Back to Homepage</Button>
            </Link>
        </div>
    );
}
