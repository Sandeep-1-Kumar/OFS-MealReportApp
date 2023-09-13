import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupSiteuserComponent } from './signup-siteuser.component';

describe('SignupSiteuserComponent', () => {
  let component: SignupSiteuserComponent;
  let fixture: ComponentFixture<SignupSiteuserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SignupSiteuserComponent]
    });
    fixture = TestBed.createComponent(SignupSiteuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
