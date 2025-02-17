import {Component, inject} from "@angular/core";
import {RouterModule} from "@angular/router";
import {SplitterModule} from 'primeng/splitter';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelMenuComponent} from "./shared/ui/panel-menu/panel-menu.component";
import {CartService} from "./cart/cart.service";
import {CurrencyPipe, NgForOf, NgIf} from "@angular/common";
import {DialogModule} from "primeng/dialog";
import {CardModule} from "primeng/card";
import {Button} from "primeng/button";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, NgIf, CurrencyPipe, DialogModule, CardModule, Button, NgForOf],
})
export class AppComponent {
  title = "ALTEN SHOP";

  protected readonly cartService = inject(CartService);

  public isCartVisible = false;

  get cartCount(): number {
    return this.cartService.cart().reduce((total, product) => total + product.quantity, 0);
  }
  public openCart() {
    this.isCartVisible = true;
  }

}
