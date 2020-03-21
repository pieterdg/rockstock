import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { SpecimenComponent } from './specimen.component';
import { SpecimenDetailComponent } from './specimen-detail.component';
import { SpecimenUpdateComponent } from './specimen-update.component';
import { SpecimenDeleteDialogComponent } from './specimen-delete-dialog.component';
import { specimenRoute } from './specimen.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(specimenRoute)],
  declarations: [SpecimenComponent, SpecimenDetailComponent, SpecimenUpdateComponent, SpecimenDeleteDialogComponent],
  entryComponents: [SpecimenDeleteDialogComponent]
})
export class RockstockSpecimenModule {}
