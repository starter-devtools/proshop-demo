import { ProductResponse } from "./ProductResponse";

export interface CartResponse {
  cartItems: ProductResponse[];
  itemsPrice: number;
  shippingPrice: number;
  taxPrice: number;
  totalPrice: number;
}

export const initialCart = {
  cartItems: [],
  itemsPrice: 0,
  shippingPrice: 0,
  taxPrice: 0,
  totalPrice: 0,
};
