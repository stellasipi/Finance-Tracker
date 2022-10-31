import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PocketAddComponent } from './pocket-add.component';

describe('AddComponent', () => {
  let component: PocketAddComponent;
  let fixture: ComponentFixture<PocketAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PocketAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PocketAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
