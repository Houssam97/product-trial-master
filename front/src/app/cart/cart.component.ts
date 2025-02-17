import { Component, OnInit } from '@angular/core';
import { Product } from 'app/products/data-access/product.model';
import {CartService} from "./cart.service";
import {CurrencyPipe} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {Button} from "primeng/button";
import {CardModule} from "primeng/card";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  standalone: true,
  imports: [
    CurrencyPipe,
    FormsModule,
    Button,
    CardModule
  ],
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  public cart: Product[] = [];
  public totalPrice: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.cart = this.cartService.cart();
    this.totalPrice = this.cartService.getTotalPrice();
  }

  onQuantityChange(productId: number, quantity: number) {
    this.cartService.updateQuantity(productId, quantity);
  }

  onRemove(productId: number) {
    this.cartService.removeFromCart(productId);
  }

  getTotalPrice(): number {
    return this.totalPrice;
  }
}
