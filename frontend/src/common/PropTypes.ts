import { InputEvent } from './EventTypes';

export interface StyleProp {
  styles: string;
}

export interface IconProps extends StyleProp {
  testId: string;
  onClick: () => void;
}

export interface InputProps extends StyleProp {
  inputType: string;
  placeholderText: string;
  onClick: () => void;
  onChange: (e: InputEvent) => void;
}

export interface ButtonProps extends StyleProp {
  type: string;
  onClick: () => void;
}

export type Product = {
  _id: string,
  name: string,
  image: string,
  description: string,
  brand: string,
  category: string,
  price: number,
  countInStock: number,
  rating: number,
  numReviews: number,
}

export interface ProductProps extends StyleProp {
  product: Product
}
