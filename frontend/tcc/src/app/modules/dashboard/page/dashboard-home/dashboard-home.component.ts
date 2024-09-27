import { Component, OnDestroy, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Subject, take, takeUntil } from 'rxjs';
import { GetAllProductsResponse } from 'src/app/models/interfaces/products/response/GetAllProductsReponse';
import { ProductsService } from 'src/app/services/product/products.service';
import { ProductsDataTransferService } from 'src/app/shared/service/products/products-data-transfer.service';

@Component({
  selector: 'app-dashboard-home',
  templateUrl: './dashboard-home.component.html',
  styleUrls: [],
})
export class DashboardHomeComponent implements OnInit, OnDestroy{

  public productList: Array<GetAllProductsResponse> = [];
  private destroy$ = new Subject<void>;

  constructor(
    private productService: ProductsService,
    private messageService: MessageService,
    private productDataTransfer: ProductsDataTransferService
  ){}

  ngOnInit(): void {
    this.getProductsDatas();
  }

  getProductsDatas(): void {
    this.productService.getAllProducts()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe({
      next: (response) => {
        if (response.length > 0){
          this.productList = response;
          this.productDataTransfer.setProductsDatas(this.productList);
        }
      },
      error: (err) => {
        console.log(err);
        this.messageService.add({
          severity: 'erros',
          summary: 'Erro',
          detail: 'Erro ao buscar produtos',
          life: 2500,
        });
      },
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete;
  }
}
