import {Component, inject, OnInit, signal} from "@angular/core";
import {Product} from "app/products/data-access/product.model";
import {ProductsService} from "app/products/data-access/products.service";
import {ProductFormComponent} from "app/products/ui/product-form/product-form.component";
import {CartService} from "../../../cart/cart.service";
import {ButtonModule} from "primeng/button";
import {CardModule} from "primeng/card";
import {DataViewModule} from 'primeng/dataview';
import {DialogModule} from 'primeng/dialog';
import {CurrencyPipe} from "@angular/common";
import {PaginatorModule} from "primeng/paginator";
import {InputTextModule} from "primeng/inputtext";


const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, CurrencyPipe, ProductFormComponent, PaginatorModule, InputTextModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService);

  public readonly products = this.productsService.products;
  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  public filteredProducts: Product[] = [];
  public productFilter: string = "";
  public page: number = 1;
  public rows: number = 5;


  ngOnInit() {
    this.productsService.get().subscribe(() => {
      this.filteredProducts = this.products();
    });
    this.applyFilter();

  }

  applyFilter() {
    this.filteredProducts = this.productsService.products().filter((product) =>
      product.name.toLowerCase().includes(this.productFilter.toLowerCase())
    );
    this.page = 0
  }

  onPageChange(event: any) {
    this.page = event.first / this.rows;
  }

  getPaginatedProducts(): Product[] {
    const start = this.page * this.rows;
    const end = start + this.rows;
    return this.filteredProducts.slice(start, end);
  }

  get totalPages() {
    return Math.ceil(this.filteredProducts.length / this.rows);
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public onAddToCart(product: Product) {
    this.cartService.addToCart(product);
  }

  private onRemoveFromCart(productId: number) {
    this.cartService.removeFromCart(productId);
  }

  adjustQuantity(product: Product, quantity: number) {
    if (quantity <= 0) {
      this.onRemoveFromCart(product.id);
    } else {
      this.cartService.updateCartProduct(product.id, quantity);
    }
  }


}
