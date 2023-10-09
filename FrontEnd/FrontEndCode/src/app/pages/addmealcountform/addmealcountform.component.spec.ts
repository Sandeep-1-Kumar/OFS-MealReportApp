import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmealcountformComponent } from './addmealcountform.component';

describe('AddmealcountformComponent', () => {
  let component: AddmealcountformComponent;
  let fixture: ComponentFixture<AddmealcountformComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddmealcountformComponent]
    });
    fixture = TestBed.createComponent(AddmealcountformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
