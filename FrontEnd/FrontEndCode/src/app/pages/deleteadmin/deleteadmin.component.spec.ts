import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteadminComponent } from './deleteadmin.component';

describe('DeleteadminComponent', () => {
  let component: DeleteadminComponent;
  let fixture: ComponentFixture<DeleteadminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteadminComponent]
    });
    fixture = TestBed.createComponent(DeleteadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
