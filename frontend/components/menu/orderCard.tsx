import { Separator } from "@/components/ui/separator";
import { ItemIngredients } from "@/types/ItemTypes";
import { Order } from "@/types/OrderTypes";

export default function OrderCard(props: { order: Order }) {
    const items = props.order.items;
    return (
        <div className="bg-zinc-100 dark:bg-gray-950 flex flex-col p-5 rounded shadow-lg w-[500px] min-h-52">
            <div className="flex justify-between">
                <h1 className="text-2xl font-semibold">#{props.order.order.orderId}</h1>
                <p className="text-xl font-semibold">Order Time: {new Date(props.order.order.scheduledTime).toLocaleTimeString()}</p>
            </div>
            <Separator />
            <div className="my-5">
                {
                    items.map((item, index) => {
                        const itemIngredients = item.itemIngredients;
                        return (
                            <div key={index}>
                                <h2 className="text-xl font-bold mb-2">{item.item.name}</h2>
                                {
                                    itemIngredients.map((itemIngredient, idx) => {
                                        return (
                                            <div key={idx} className="flex items-center justify-between ps-2">
                                                <p className="font-semibold">{itemIngredient.ingredientDTO.name}</p>
                                                <p>{itemIngredient.quantity}</p>
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        )
                    })
                }

            </div>
            <div className="flex justify-between items-center">
                <p>Order Method: <span className="text-green-500">{props.order.order.pickupMethod}</span></p>
            </div>
        </div>
    )
}
