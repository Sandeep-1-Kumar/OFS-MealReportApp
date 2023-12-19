import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateadminpasswordComponent } from './updateadminpassword.component';

describe('UpdateadminpasswordComponent', () => {
  let component: UpdateadminpasswordComponent;
  let fixture: ComponentFixture<UpdateadminpasswordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateadminpasswordComponent]
    });
    fixture = TestBed.createComponent(UpdateadminpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
