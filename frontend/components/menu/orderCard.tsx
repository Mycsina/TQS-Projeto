"use client"

import { Order, OrderStatus } from "@/types/OrderTypes";
import { useState } from "react";
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "../ui/card";
import { Badge } from "../ui/badge";
import { Button } from "../ui/button";

export default function OrderCard(props: { order: Order }) {
  const { order, items } = props.order
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const closeDropdown = () => {
    setIsOpen(false);
  };

  return (
    <Card key={order.orderId}>
      <CardHeader>
        <CardTitle>{order.orderId}</CardTitle>
        <div className="text-sm text-gray-500 dark:text-gray-400">{order.scheduledTime}</div>
      </CardHeader>
      <CardContent>
        <div className="flex justify-between items-center mb-2">
          <div className="font-medium">{order.userId}</div>
          <Badge
            variant="secondary"
          >
            {order.status}
          </Badge>
        </div>
        <div className="border-b border-gray-200 dark:border-gray-700 my-4"></div>
        <div className="text-lg font-bold">${order.price}</div>
        <div className="mt-4">
          <ul className="space-y-2">
            { /*items.map((item, index) => (
              <li key={index}>
                <div className="flex justify-between">
                  <span>{item.name}</span>
                  <span>
                    {item.quantity}
                  </span>
                </div>
              </li>
            ))*/}
          </ul>
        </div>
      </CardContent>
      <CardFooter className="justify-between">
        <Button className="" variant="outline">
          View Order
        </Button>
        <Button className="" variant="outline" onClick={toggleDropdown}>
          Actions
        </Button>
        {isOpen && (
          <div className="origin-top-right absolute right-0 mt-2 w-44 rounded-lg shadow-lg bg-white ring-1 ring-black ring-opacity-5">
            <ul role="menu" aria-orientation="vertical" aria-labelledby="actions-menu">
              <li>
                <a
                  href="#"
                  // yellow button
                  className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 bg-yellow-500 rounded-t-lg"
                  onClick={() => {
                    console.log('Suspend Order');
                    closeDropdown();
                  }}
                >
                  Suspend Order
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 bg-red-500 rounded-b-lg"
                  onClick={() => {
                    console.log('Cancel Order');
                    closeDropdown();
                  }}
                >
                  Cancel Order
                </a>
              </li>
            </ul>
          </div>
        )}
      </CardFooter >
    </Card >
  )
}
