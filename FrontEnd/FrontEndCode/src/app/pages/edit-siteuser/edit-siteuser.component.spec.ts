import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSiteuserComponent } from './edit-siteuser.component';

describe('EditSiteuserComponent', () => {
  let component: EditSiteuserComponent;
  let fixture: ComponentFixture<EditSiteuserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditSiteuserComponent]
    });
    fixture = TestBed.createComponent(EditSiteuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
