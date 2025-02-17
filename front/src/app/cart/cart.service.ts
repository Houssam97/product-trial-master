import { Injectable } from '@angular/core';
import { Product } from 'app/products/data-access/product.model';
import { signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly _cart = signal<Product[]>([]);

  public readonly cart = this._cart;

  public addToCart(product: Product) {
    console.log(product);
    const cartProducts = [...this._cart()];
    const existingProductIndex = cartProducts.findIndex(p => p.id === product.id);

    if (existingProductIndex > -1) {
      cartProducts[existingProductIndex].quantity += 1;
    } else {
      cartProducts.push({ ...product, quantity: 1 });
    }

    this._cart.set(cartProducts);
  }
  public getCartCount(): number {
    return this._cart().reduce((total, product) => total + product.quantity, 0);
  }

  public removeFromCart(productId: number) {
    const updatedCart = this._cart().filter(product => product.id !== productId);
    this._cart.set(updatedCart);
  }
  updateQuantity(productId: number, quantity: number) {
    this._cart.update(cart => {
      const product = cart.find(p => p.id === productId);
      if (product) {
        product.quantity = quantity;
      }
      return [...cart];
    });
  }
  public updateCartProduct(productId: number, quantity: number) {
    const updatedCart = this._cart().map(product => {
      if (product.id === productId) {
        return { ...product, quantity };
      }
      return product;
    });
    this._cart.set(updatedCart);
  }

  getTotalPrice(): number {
    return this._cart().reduce((total, product) => total + product.price * product.quantity, 0);
  }
}
