import OrderCard from "@/components/menu/orderCard";

export default function DashBoard() {
    return (
        <div className="flex flex-col">
            <h1 className="font-bold text-2xl mb-5">Incoming Orders</h1>
            <div className="grid gap-8 lg:grid-cols-2 2xl:grid-cols-3">
                {

                    Array(10).fill(0).map((_, index) => (
                        <OrderCard key={index} />
                    ))
                }

            </div>
        </div>
    );
}
