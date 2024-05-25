export type ItemIngredients = {
    item: Item,
    ingredients: Ingredients,
    quantity: number
}

export type Item = {
    name: string
    restaurantId: number
    categoryId: number
}

export type Ingredients = {
    name: string
    restaurantId: number
}
