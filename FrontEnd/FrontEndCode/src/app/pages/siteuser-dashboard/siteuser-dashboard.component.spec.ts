import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteuserDashboardComponent } from './siteuser-dashboard.component';

describe('SiteuserDashboardComponent', () => {
  let component: SiteuserDashboardComponent;
  let fixture: ComponentFixture<SiteuserDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SiteuserDashboardComponent]
    });
    fixture = TestBed.createComponent(SiteuserDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
