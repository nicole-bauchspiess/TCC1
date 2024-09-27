import { Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { ProductsService } from 'src/app/services/product/products.service';
import { ProductsDataTransferService } from 'src/app/shared/service/products/products-data-transfer.service';
import { GetAllProductsResponse } from 'src/app/models/interfaces/products/response/GetAllProductsReponse';
import { MessageService } from 'primeng/api';
import { EventAction } from 'src/app/models/interfaces/products/event/EventAction';

@Component({
  selector: 'app-products-home',
  templateUrl: './products-home.component.html',
  styleUrls: []
})
export class ProductsHomeComponent implements OnInit, OnDestroy {
  private readonly destroy$: Subject<void> = new Subject();
  public productDatas: Array<GetAllProductsResponse> = [];

  constructor(
    private prodService: ProductsService,
    private prodDataTransfer: ProductsDataTransferService,
    private router:Router,
    private messageService: MessageService
  ){}

  ngOnInit(): void {
    this.getServiceProductsDatas();
  }

  getServiceProductsDatas() {
    const productsLoad = this.prodDataTransfer.getProductsDatas();

    if (productsLoad.length > 0) {
      this.productDatas = productsLoad;
    }
    else {
      this.getAPIProductDatas();
    }
    console.log('DADOS DE PRODUTOS', this.productDatas)
  }

  getAPIProductDatas() {
    this.prodService.getAllProducts()
    .pipe(takeUntil(this.destroy$))
    .subscribe({
      next: (response) => {
        if (response.length > 0) {
          this.productDatas = response;
        }
      },
      error: (err) => {
        console.log(err);
        this.router.navigate(['/dashboard']);
        this.messageService.add(
          {
            severity: 'error',
            summary: 'Erro',
            detail: 'Erro ao buscar produtos',
            life: 2500
          }
        );
      },
    });
  }

  handleProductAction (event: EventAction): void{
    console.log('DADOS DO EVENTO', event)
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
