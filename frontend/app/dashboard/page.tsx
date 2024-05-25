import OrderCard from "@/components/menu/orderCard";
import { getAllOrders } from "@/server/OrderData";
import { redirect } from "next/navigation";

export default async function DashBoard() {
    const data = await getAllOrders();

    if (data === null) {
        return redirect("/not-found");
    }

    return (
        <div className="flex justify-between h-full min-h-screen">
            <div className="flex flex-col">
                <h1 className="font-bold text-2xl mb-5">Incoming Orders</h1>
                <div className="grid gap-8 lg:grid-cols-2">
                    {
                        Array(10).fill(0).map((_, index) => (
                            <OrderCard key={index} />
                        ))
                    }
                </div>
            </div>
            <div className="bg-gray-950 w-[500px] h-full min-h-screen rounded-xl shadow-xl p-10">
                <h1 className="text-4xl mb-10">Instructions</h1>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
                <p>Lorem Ipsum</p>
            </div>
        </div>
    );
}
