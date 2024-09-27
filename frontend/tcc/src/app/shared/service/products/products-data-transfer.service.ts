import { Injectable } from '@angular/core';
import { BehaviorSubject, map, take } from 'rxjs';
import { GetAllProductsResponse } from 'src/app/models/interfaces/products/response/GetAllProductsReponse';

@Injectable({
  providedIn: 'root'
})
export class ProductsDataTransferService {

  public productsDataEmitter$ = new BehaviorSubject<GetAllProductsResponse[] | null>(null);
  public productsDatas: Array<GetAllProductsResponse> = [];

  setProductsDatas(products: Array<GetAllProductsResponse>): void {
    if (products) {
      //next -> emite um novo dado para quem está inscrito no observable
      this.productsDataEmitter$.next(products);
      this.getProductsDatas();
    }
  }

  getProductsDatas() {
    this.productsDataEmitter$
    .pipe(
      take(1),
      map((product) => product?.filter((data) => data.amount > 0))
    )
    .subscribe({
      next: (response) => {
        if (response){
          this.productsDatas = response;
        }

      },
    });
    return this.productsDatas;
  }

}
