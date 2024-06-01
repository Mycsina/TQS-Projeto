"use client";

import React, { useMemo, useState } from "react";

import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";

import { Order, OrderStatus } from "@/types/OrderTypes";
import OrderCard from "@/components/menu/orderCard";

export default function Dashboard(props: { orders: Order[] }) {
  const { orders } = props;

  const [selectedStatus, setSelectedStatus] = useState("all")

  const filteredOrders = useMemo(() => {
    if (selectedStatus === "all") {
      return orders
    } else {
      return orders.filter((orders) => orders.order.status === selectedStatus)
    }
  }, [selectedStatus, orders])

  return (
    <div className="flex flex-col h-full">
      <header className="bg-gray-100 dark:bg-gray-800 px-6 py-4 border-b border-gray-200 dark:border-gray-700">
        <Tabs value={selectedStatus} onValueChange={setSelectedStatus}>
          <TabsList className="flex gap-4">
            <TabsTrigger value="all">All</TabsTrigger>
            {Object.values(OrderStatus).map((status) => (
              <TabsTrigger key={status} value={status}>
                {status}
              </TabsTrigger>
            ))}
          </TabsList>
        </Tabs>
      </header>
      <main className="flex-1 p-6">
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {filteredOrders.map((order) => (
            <OrderCard key={order.order.orderId} order={order} />
          ))}
        </div>
      </main>
    </div>
  )
}