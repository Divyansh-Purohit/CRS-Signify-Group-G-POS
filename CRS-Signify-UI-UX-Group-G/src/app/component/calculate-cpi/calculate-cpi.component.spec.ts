import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculateCpiComponent } from './calculate-cpi.component';

describe('CalculateCpiComponent', () => {
  let component: CalculateCpiComponent;
  let fixture: ComponentFixture<CalculateCpiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculateCpiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculateCpiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
