import { getAllOrders } from "@/server/OrderData"
import Dashboard from "@/components/dashboard"

export const revalidate = 0;

export default async function Component() {
  const orders = await getAllOrders();
  if (orders === null) {
    return (
      <div className="flex">
        <h1 className="text-4xl text-red-600">Failed to get Orders</h1>
      </div>
    )
  }


  return (
    <Dashboard orders={orders} />
  )
}
