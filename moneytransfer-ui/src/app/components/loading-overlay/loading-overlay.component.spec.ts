import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadingOverlayComponent } from './loading-overlay.component';
import { NgxSpinnerModule } from 'ngx-spinner';

describe('LoadingOverlayComponent', () => {
  let component: LoadingOverlayComponent;
  let fixture: ComponentFixture<LoadingOverlayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        NgxSpinnerModule
      ],
      declarations: [
        LoadingOverlayComponent
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadingOverlayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
