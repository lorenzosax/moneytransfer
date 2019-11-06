import {Component, Input, OnChanges, OnInit, SimpleChange, SimpleChanges} from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-loading-overlay',
  templateUrl: './loading-overlay.component.html',
  styleUrls: ['./loading-overlay.component.less']
})
export class LoadingOverlayComponent implements OnInit, OnChanges {

  constructor(private spinner: NgxSpinnerService) { }

  @Input() loading = false;

  ngOnInit() {
    console.log('LoadingOverlayComponent -> init component');
    // setTimeout is used for get out from angular lifecicle,
    // because the component of the library used is not implementing ngOnChanges
    setTimeout(() => this.loading ? this.spinner.show() : null, 1);
  }

  ngOnChanges(changes: SimpleChanges): void {
    const loading: SimpleChange = changes.loading;
    console.log('LoadingOverlayComponent -> ' + loading.currentValue);
    if (loading.currentValue) {
      console.log('LoadingOverlayComponent -> Show branch -> change loading ' + loading.currentValue);
      this.spinner.show();
    } else {
      console.log('LoadingOverlayComponent -> Hide branch -> change loading ' + loading.currentValue);
      this.spinner.hide();
    }
  }

}
