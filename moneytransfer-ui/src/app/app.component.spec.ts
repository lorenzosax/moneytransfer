import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NgxSpinnerModule } from 'ngx-spinner';
import { TopbarComponent } from './components/topbar/topbar.component';
import { BonificoPageComponent } from './pages/bonifico-page/bonifico-page.component';
import { LoadingOverlayComponent } from './components/loading-overlay/loading-overlay.component';
import { NgbDateToStringFormatPipe } from './utils/pipe/ngb-date-to-string-format.pipe';
import { SummaryBonificoComponent } from './pages/bonifico-page/summary-bonifico/summary-bonifico.component';
import { HomePageComponent } from './pages/home-page/home-page.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientTestingModule,
        NgbModule,
        FormsModule,
        NgxSpinnerModule,
        FontAwesomeModule
      ],
      declarations: [
        AppComponent,
        TopbarComponent,
        BonificoPageComponent,
        LoadingOverlayComponent,
        NgbDateToStringFormatPipe,
        SummaryBonificoComponent,
        HomePageComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
