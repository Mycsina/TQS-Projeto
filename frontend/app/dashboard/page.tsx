import OrderCard from "@/components/menu/orderCard";
import { getInMaking } from "@/server/OrderData";
import { redirect } from "next/navigation";

export const revalidate = 0;

export default async function DashBoard() {
    const data = await getInMaking();

    if (data === null) {
        // TODO: Show an error
        return redirect("/not-found");
    }

    return (
        <div className="flex justify-between h-full min-h-screen mx-auto w-fit">
            <div className="flex flex-col">
                <h1 className="font-bold text-2xl mb-5 w-max">Incoming Orders</h1>
                <div className="grid gap-8 lg:grid-cols-3">
                    {
                        data.map((order, index) => (
                            <OrderCard key={index} order={order} />
                        ))
                    }
                </div>
            </div>
        </div>
    );
}
