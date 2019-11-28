import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { NgxSpinnerModule } from 'ngx-spinner';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { BonificoPageComponent } from './pages/bonifico-page/bonifico-page.component';
import { LoadingOverlayComponent } from './components/loading-overlay/loading-overlay.component';
import { NgbDateToStringFormatPipe } from './utils/pipe/ngb-date-to-string-format.pipe';
import { SummaryBonificoComponent } from './pages/bonifico-page/summary-bonifico/summary-bonifico.component';
import { HomePageComponent } from './pages/home-page/home-page.component';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    BonificoPageComponent,
    LoadingOverlayComponent,
    NgbDateToStringFormatPipe,
    SummaryBonificoComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    NgxSpinnerModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    library.add(faCalendar);
  }
}
